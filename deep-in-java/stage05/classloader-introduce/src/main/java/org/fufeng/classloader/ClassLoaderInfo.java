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
 * @description: {@link ClassLoader} 字节码加载器
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-28
 * @see ClassLoader
 */
public class ClassLoaderInfo {

    public static void main(String[] args) {
        // 系统 ClassLoader
        // jdk11 jdk.internal.loader.ClassLoaders$AppClassLoader@2c13da15
        // jdk8 sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(ClassLoader.getSystemClassLoader()); // 只读

        // 应用 ClassLoader
        // jdk11 jdk.internal.loader.ClassLoaders$AppClassLoader@2c13da15
        // jdk8 sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(Thread.currentThread().getContextClassLoader()); // 可修改

        // 如何实现类隔离?
        // 通过上面可以看出可以修改Thread 上下文 ClassLoader
        //final ClassLoader previousClassLoader =
        //        Thread.currentThread().getContextClassLoader();

        // previousClassLoader 能够加载 User.class V1 版本（user-api-1.0.0.jar 文件中，在 /classpath1 目录下）
        // User.class V2 版本 （user-api-2.0.0.jar 文件中，在 /classpath2 目录下）
        // loadUser 操作加载 User.class V2 版本

        // previousClassLoader ClassPath -> /classpath1
        // newClassLoader  ClassPath -> /classpath2

        // 通常，系统或者应用（包括自定义） ClassLoader 均为 URLClassLoader 子类

        loadUser();
    }

    private static void changeClassLoader(ClassLoader classLoader){
        // 获取当前线程
        Thread thread = new Thread();
        // 当前ClassLoader无法加载User.class类,不过该类能被newClassLoader加载
        final ClassLoader contextClassLoader = thread.getContextClassLoader();

        try {
            // 切换当前线程的类加载器
            thread.setContextClassLoader(classLoader); // 需要setContextClassLoader权限
            // 利用新的ClassLoader使用
        }finally {
            thread.setContextClassLoader(contextClassLoader);
        }
    }

    // 兼容或者适配老版ClassLoader
    private static void loadUser(){
        // JAXB 通过线程上下文ClassLoader切换不同实现的SPI
        // JAXB 1.X 2.X
        // JDK 提供API 1.X
        // 第三方包实现 2.X
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            // 使用不同的User实现
            classLoader.loadClass("org.fufeng.classloader.ClassLoaderInfo.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public class User{

    }

}
