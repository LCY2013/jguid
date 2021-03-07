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
package org.fufeng.jvm.classloader.demo03.example;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 测试两个类加载器
 * @create 2021-03-07
 * @since 1.0
 */
public class Main {

    private static final String PATH = "/Users/magicLuoMacBook/software/java/ideawork/im/gitlab/jguid/java-advanced/src/main/java/org/fufeng/jvm/classloader/demo03/example/Person.java";

    private static final String CLASS_FULL_NAME = "org.fufeng.jvm.classloader.demo03.example.Person";

    public static void main(String[] args) throws Exception {
        System.out.println("DiskClassLoader.");
        testDiskClassLoader();

        System.out.println("---------------------");
        System.out.println("ExtClasspathLoader.");

        testExtClasspathLoader();

    }

    private static void testExtClasspathLoader() throws Exception {
        ExtClasspathLoader.loadClasspath(new File(PATH));

        Object obj = Class.forName(CLASS_FULL_NAME).newInstance();

        System.out.println(obj);
    }

    private static void testDiskClassLoader() {
        // 创建自定义classloader对象
        DiskClassLoader diskLoader = new DiskClassLoader(PATH);
        try {
            // 加载class文件
            Class c = diskLoader.loadClass(CLASS_FULL_NAME);

            if (c != null) {
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say", null);
                    //通过反射调用Test类的say方法
                    method.invoke(obj, null);
                    System.out.println(obj);
                } catch (InstantiationException | IllegalAccessException
                        | NoSuchMethodException
                        | SecurityException |
                        IllegalArgumentException |
                        InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}