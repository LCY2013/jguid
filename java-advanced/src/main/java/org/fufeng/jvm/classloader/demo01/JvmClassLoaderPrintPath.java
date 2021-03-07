/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2021-03-07
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.jvm.classloader.demo01;

import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description jvm 类加载器输出，jdk1.8版本用例
 * @create 2021-03-07
 * @since 1.0
 */
public class JvmClassLoaderPrintPath {

    public static void main(String[] args) {
        // 启动类加载器
        final URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for (URL url : urLs) {
            System.out.println(" ==> " + url.toExternalForm());
        }

        // 扩展类加载器
        printClassLoader("扩展类加载器", JvmClassLoaderPrintPath.class.getClassLoader().getParent());

        // 应用类机载器
        printClassLoader("应用类机载器", JvmClassLoaderPrintPath.class.getClassLoader());
    }

    /**
     * 打印classLoader相关信息
     *
     * @param classLoaderName 名称
     * @param classLoader     类加载器
     */
    private static void printClassLoader(String classLoaderName, ClassLoader classLoader) {
        if (Objects.nonNull(classLoader)) {
            System.out.println(classLoaderName + " -> " + classLoader.toString());
            printUrlForClassLoader(classLoader);
        } else {
            System.out.println(classLoaderName + " -> null");
        }
    }

    /**
     * 打印classLoader加载的路径
     *
     * @param classLoader 类加载器
     */
    private static void printUrlForClassLoader(ClassLoader classLoader) {
        final Object ucp = insightField(classLoader, "ucp");
        final Object path = insightField(ucp, "path");
        ArrayList<Object> ps = (ArrayList<Object>) path;
        for (Object obj : ps) {
            System.out.println(" ==> " + obj.toString());
        }
    }

    /**
     * 判断某个对象是否含有某个字段,并返回这个字段
     *
     * @param obj       某个对象
     * @param fieldName 字段名称
     */
    private static Object insightField(Object obj, String fieldName) {
        try {
            Field field = null;
            if (obj instanceof URLClassLoader) {
                field = URLClassLoader.class.getDeclaredField(fieldName);
            } else {
                field = obj.getClass().getDeclaredField(fieldName);
            }
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
