/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-28
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.classloader;

/**
 * @program: jguid
 * @description: {@link ClassLoader} 与 ClassPath
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-28
 * @see ClassLoader
 */
public class ClassLoaderAndClassPathInfo {

    public static void main(String[] args) {
        // 通常使用，在JVM进程启动时加上 -verbose:class 参数来显示加载的Class
        // 类[[0.201s][info][class,load] java.util.Formattable source: jrt:/java.base
        //class org.fufeng.classloader.ClassLoaderAndClassPathInfo],资源所在位置:file:/Users/magicLuoMacBook/software/java/ideawork/im/gitlab/jguid/deep-in-java/stage05/classloader-introduce/target/classes/
        // Exception in thread "main" java.lang.NullPointerException
        //	at org.fufeng.classloader.ClassLoaderAndClassPathInfo.printClassLocation(ClassLoaderAndClassPathInfo.java:41)
        //	at org.fufeng.classloader.ClassLoaderAndClassPathInfo.main(ClassLoaderAndClassPathInfo.java:33)
        // printClassLocation(Object.class);
        // printClassLocation(int.class);

        // 类资源与URL有关联，是否意味着ClassLoader与URL有关联
        printClassLocation(ClassLoaderAndClassPathInfo.class);

        // Spring Boot Spring-boot-loader
        // 文件目录 : expose -> File Handler
        // 文件 : JAR、WAR、EAR JAR handler
        // URL JAVA 资源 抽象
            // java.net.URLStreamHandler
            // java.net.URL
    }

    private static void printClassLocation(Class<?> clazz){
        System.out.printf("类[%s],资源所在位置:%s\n",clazz,
                clazz.getProtectionDomain().getCodeSource().getLocation());
    }
}
