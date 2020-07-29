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
 * @description: {@link Class} 和 {@link ClassLoader} 介绍
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-28
 * @see Class
 * @see ClassLoader
 */
public class ClassAndClassLoaderInfo {

    public static void main(String[] args) {
        // 获取Object的ClassLoader
        // Object 被 BootstrapClassLoader加载，这里获取的ClassLoader就为null
        printClassLoader(Object.class);

        // 获取原生的基础类型int的ClassLoader
        // int 被 BootstrapClassLoader 加载，这里获取的ClassLoader就为null
        printClassLoader(int.class);

        // 获取当前类的ClassLoader
        // 当前类被AppClassLoader 加载
        printClassLoader(ClassAndClassLoaderInfo.class);

        // 查看当前类的类加载器(ClassLoader)是否与当前系统的类加载器一致？
        System.out.println(ClassAndClassLoaderInfo.class.getClassLoader()
                == ClassLoader.getSystemClassLoader());
        // 这里条件 > JDK1.4
        System.out.println(ClassLoader.getSystemClassLoader());
        // 这里条件 > JDK9
        //System.out.println(ClassLoader.getPlatformClassLoader());
    }

    private static void printClassLoader(Class<?> clazz){
        System.out.printf("类-[%s] 被 类加载器-[%s]加载\n",
                clazz,clazz.getClassLoader());
    }

}
