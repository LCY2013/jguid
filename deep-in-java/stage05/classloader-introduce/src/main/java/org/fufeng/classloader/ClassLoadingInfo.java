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

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: 类加载过程
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-28
 * @see ClassLoader
 */
public class ClassLoadingInfo {

    public static void main(String[] args) throws ClassNotFoundException {

        // 当前 main 线程 ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 加载某个 Class 对象
        // User user = ... -> load class

        // 当前工程相对路径：stage05/classloader-introduce
        // 当前工程绝对路径：${user.dir}/stage05/classloader-introduce
        // 当前工程ClassPath ：${user.dir}/stage05/classloader-introduce/target/classes
        // User 类全名：org.fufeng.classloader.User
        // User.class 文件路径：${ClassPath}/org/fufeng/classloader/User.class

        String className = "org.fufeng.classloader.User";
        // org/fufeng/classloader/User.class
        String classFileName = className.replace('.', '/').concat(".class");
        String classPath = System.getProperty("user.dir") + "/deep-in-java/stage05/classloader-introduce/target/classes";
        // User.class 类文件的绝对路径
        File classFile = new File(classPath, classFileName);

        // ClassLoader 也是对象，也会被 GC 管理
        MyClassLoader myClassLoader = new MyClassLoader();
        // .class 文件变为字节流 byte[]，再定义 Class 对象
        Class<?> userClass = myClassLoader.defineClass(className, classFile);

        System.out.println("当前类对象：" + userClass);
        Stream.of(userClass.getDeclaredFields())
                .forEach(field -> {
                    System.out.println("当前字段信息：" + field);
                });

        Class<?> userClassFromThreadContextClassLoader = classLoader.loadClass(className);
        // User.class 被 MyClassLoader 加载后，是否与线程上下文加载的 User.class 对象是否一致？
        // 这个现象能够解释 Spring spring-boot-devtools 模块 Class!=Class 问题
        System.out.println("userClass == userClassFromThreadContextClassLoader ? "
                + (userClass == userClassFromThreadContextClassLoader));

        // 重新替换掉线程上下文 ClassLoader
        // myClassLoader -> Thread.currentThread().getContextClassLoader()
        Thread.currentThread().setContextClassLoader(myClassLoader);
        // 老的线程上下文 ClassLoader 是 MyClassLoader 的 parent，由于双亲委派，及时是 MyClassLoader 重新调用
        // loadClass(String) 方法，也不会重新加载
        Class<?> userClassFromMyClassLoader = classLoader.loadClass(className);
        System.out.println("userClass == userClassFromMyClassLoader ? " +
                (userClass == userClassFromMyClassLoader));

        // 已加载 Class 是如何实现，目标方法： java.lang.ClassLoader.findLoadedClass0
        System.out.println(
                "userClassFromThreadContextClassLoader == userClassFromMyClassLoader ? " +
                        (userClassFromThreadContextClassLoader == userClassFromMyClassLoader));

        System.out.println(userClassFromMyClassLoader.getClassLoader());
        System.out.println(userClass.getClassLoader());
    }

    static class MyClassLoader extends ClassLoader {

        public MyClassLoader() {
            // 当前线程上下文 ClassLoader 作为 Parent
            super(Thread.currentThread().getContextClassLoader());
        }

        // 文件 -> 定义某个 Class
        public Class<?> defineClass(String name, File classFile) {
            // File classFile -> byte[]
            byte[] bytes = loadBytes(classFile);
            // 利用 ClassLoader defineClass 方法来定义 Class
            // 可用于动态加载
            return super.defineClass(name, bytes, 0, bytes.length);

        }

        private byte[] loadBytes(File classFile) {
            byte[] bytes = null;
            try {
                bytes = FileUtils.readFileToByteArray(classFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return bytes;
        }
    }
}
