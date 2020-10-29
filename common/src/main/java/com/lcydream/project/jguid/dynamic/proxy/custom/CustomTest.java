package com.lcydream.project.jguid.dynamic.proxy.custom;

import com.lcydream.project.jguid.dynamic.proxy.custom.business.CustomInterface;
import com.lcydream.project.jguid.dynamic.proxy.custom.business.CustomLoggerFmt;
import com.lcydream.project.jguid.dynamic.proxy.custom.proxy.CustomClassloader;
import com.lcydream.project.jguid.dynamic.proxy.custom.proxy.CustomProxy;

import java.util.Date;

/**
 * @ClassName CustomTest
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 6:53 下午
 **/
public class CustomTest {

    public static void main(String[] args) throws Exception {
        //生产JDK接口代理的类
        /*
        import com.lcydream.project.jguid.dynamic.proxy.custom.business.CustomInterface;
        import sun.misc.ProxyGenerator;
        import java.io.FileOutputStream;
        final byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{CustomInterface.class});
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/a/softwork/java/ideawork/im/gitlab/jguid/src/main/java/com/lcydream/project/jguid/dynamic/proxy/custom/generator/$Proxy0.class");
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();*/
        /*final Method[] methods = CustomTest.class.getMethods();
        //方法名称
        System.out.println(methods[0].getName());
        //修饰符数字形式
        System.out.println(methods[0].getModifiers());
        //方法参数个数
        System.out.println(methods[0].getParameterCount());
        //方法返回值
        System.out.println(methods[0].getReturnType());
        //方法参数集合
        System.out.println(methods[0].getParameters()[0].getType());

        System.out.println();

        for (Method m : methods){
            System.out.println(m.getName()+":"+m.getModifiers());
        }*/

        final CustomInterface customInterface =
                (CustomInterface)CustomProxy.newProxyInstance(new CustomClassloader(),
                        new Class[]{CustomInterface.class}, (proxy, method, args1) -> {
                            System.out.println("before");
                            final Object invoke = method.invoke(new CustomLoggerFmt(),args1);
                            System.out.println("after");
                            return invoke;
                        });
        customInterface.print(new Date());
        System.out.println(customInterface.out(new Object()));
        System.out.println(customInterface.println(new Date()));
        customInterface.printF();
    }

    private void s1(){}
    public void s2(){}
    protected void s3(){}
    void s4(){}
}
