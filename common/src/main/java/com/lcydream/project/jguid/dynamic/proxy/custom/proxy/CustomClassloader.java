package com.lcydream.project.jguid.dynamic.proxy.custom.proxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @ClassName CoustomClassloader
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 5:26 下午
 **/
public class CustomClassloader extends ClassLoader{

    private File classPathFile;

    public CustomClassloader() {
        String classPath = CustomClassloader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    @Override
    public Class<?> findClass(String className){
        String clazzPath = CustomClassloader.class.getPackage().getName() + "." + className;
        if(classPathFile != null){
            File classFile = new File(classPathFile,className.replaceAll("\\.","/")+".class");
            if(classFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;

                try {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1){
                        out.write(buff,0,len);
                    }
                    return defineClass(className,out.toByteArray(),0,out.size());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        if(in != null){
                            in.close();
                        }
                        if(out != null){
                            out.close();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

}
