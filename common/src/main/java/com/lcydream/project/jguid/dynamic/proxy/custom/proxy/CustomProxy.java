package com.lcydream.project.jguid.dynamic.proxy.custom.proxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName CoustomProxy
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 5:25 下午
 **/
public class CustomProxy {

    private static String LN = "\r\n";

    private static String CLASS_PRI = "$Proxy";

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static Object newProxyInstance(CustomClassloader loader,
                                         Class<?>[] interfaces,
                                         CustomInvokeHandler h){
        String proxyClassName = CLASS_PRI+atomicInteger.getAndIncrement();
        //1、动态生成源代码.java
        String srcJava = generateSrc(proxyClassName,interfaces);

        //2、将java源文件输出到磁盘
        //获取文件路径
        final String srcPath = CustomProxy.class.getResource("").getPath();
        FileOutputStream fileOutputStream=null;
        File file = null;
        try {
            file = new File(srcPath+proxyClassName+".java");
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(srcJava.getBytes());
            fileOutputStream.flush();

            //3、将源文件编译成字节码文件
            //获取jvm字节码编译器
            final JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
            final StandardJavaFileManager standardFileManager = systemJavaCompiler.getStandardFileManager(null, null, Charset.forName("UTF-8"));
            final Iterable<? extends JavaFileObject> javaFileObjects = standardFileManager.getJavaFileObjects(file);
            final JavaCompiler.CompilationTask task =
                    systemJavaCompiler.getTask(null, standardFileManager, null, null, null, javaFileObjects);
            task.call();
            standardFileManager.close();

            //4、编译生成的字节码文件加载到JVM中
            //利用自定义类加载字节码文件
            final Class<?> aClass = loader.findClass(proxyClassName);

            //5、返回字节码重组后的新对象
            final Constructor<?> constructor = aClass.getConstructor(CustomInvokeHandler.class);
            return constructor.newInstance(h);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据接口信息生成接口代理类
     * @param interfaces 接口集合
     * @return 源代码文件
     */
    private static String generateSrc(String proxyClassName,Class<?>[] interfaces){
        StringBuffer sb = new StringBuffer();

        //导入引用信息
        for (Class<?> clazz : interfaces) {
            sb.append("import ").append(clazz.getName()).append(";").append(LN);
        }
        sb.append("import ").append("com.lcydream.project.jguid.dynamic.proxy.custom.proxy.CustomInvokeHandler;").append(LN);
        sb.append("import ").append("java.lang.reflect.Method;").append(LN);
        sb.append("import ").append("java.lang.reflect.UndeclaredThrowableException;").append(LN);

        //生成类信息
        sb.append("@SuppressWarnings(\"unchecked\")").append(LN);
        sb.append("public final class ").append(proxyClassName).append(" implements ");
        //实现接口信息
        for (Class<?> clazz : interfaces) {
            sb.append(clazz.getSimpleName()).append(",");
        }
        //删除最后一个标点,结束类的开始
        sb.delete(sb.length()-1,sb.length()).append("{").append(LN);

        //开始构造参数信息
        sb.append("private CustomInvokeHandler h;").append(LN);
        //开始构造函数
        sb.append("public ").append(proxyClassName)
                .append("(CustomInvokeHandler h)  {").append(LN);
        sb.append("this.h = h;").append("}").append(LN);

        //开始实现方法
        for (Class<?> clazz : interfaces) {
            final Method[] methods = clazz.getMethods();
            for (Method m : methods){
                sb.append("public final ")
                        .append(m.getReturnType().getSimpleName())
                        .append(" ")
                        .append(m.getName())
                        .append("(");
                final Parameter[] parameters = m.getParameters();
                for (Parameter parameter : parameters){
                    sb.append("Object ").append(parameter.getName()).append(",");
                }
                if(parameters.length > 0) {
                    sb.delete(sb.length() - 1, sb.length());
                }
                sb.append(") {").append(LN);
                //返回值确认
                //生成一个随机的参数名称
                String paramName = generateParamName();
                sb.append("Object ").append(paramName).append(" = null;");
                sb.append("try {").append(LN)
                        .append("Method m = ")
                        .append(clazz.getName())
                        .append(".class.getMethod(\"").append(m.getName()).append("\",new Class[]{");
                for (Parameter parameter : parameters){
                    sb.append(parameter.getType().getName()).append(".class").append(",");
                }
                if(parameters.length > 0) {
                    sb.delete(sb.length() - 1, sb.length());
                }
                sb.append("});")
                        .append(LN)
                        .append(paramName)
                        .append(" = this.h.invoke(this, m, new Object[]{");
                for (Parameter parameter : parameters){
                    sb.append(parameter.getName()).append(",");
                }
                if(parameters.length > 0) {
                    sb.delete(sb.length() - 1, sb.length());
                }
                sb.append("});").append("} catch (RuntimeException | Error var3) {\n" +
                        "            throw var3;\n" +
                        "        } catch (Throwable var4) {\n" +
                        "            throw new UndeclaredThrowableException(var4);\n" +
                        "        }").append(LN);
                //如果存在返回值
                if(!m.getReturnType().getSimpleName().equals("void")){
                    sb.append("return ").append(paramName).append(";");
                }
                sb.append("}");
            }
        }

        //生成类结尾
        sb.append("}");
        return sb.toString();
    }

    /**
     * 生成随机的变量名称
     * @return 变量名称
     */
    private static String generateParamName(){
        Random random = new Random();
        return "customVal"+random.nextInt(100);
    }
}
