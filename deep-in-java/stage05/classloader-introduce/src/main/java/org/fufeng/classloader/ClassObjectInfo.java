/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-29
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
 * @description: {@link Class} 类对象示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-29
 */
public class ClassObjectInfo {

    public static void main(String[] args) {
        /*
            ClassLoader 在加载过程中(验证),去重操作
            验证运行时校验.class文件 (已经编译结果)
            .class 文件在 java 1.5存在泛型，所以低版本编译不会通过
            《深入JVM虚拟机》
            双亲委派(类加载及类存储)
         */
        Class<?> objectClass = Object.class;

        // ClassLoader

        // 原生类型也有class对象
        Class<?> intClass = int.class;

        isPrimitive(objectClass);
        isPrimitive(intClass);

        // Object.class 和 int.class 均被 Bootstrap ClassLoader
        // Bootstrap ClassLoader 在 Java 9 之前，就是 rt.jar
        // 除 Bootstrap ClassLoader 之外，System ClassLoader, Application ClassLoader
    }

    private static void isPrimitive(Class<?> clazz){
        System.out.printf("class-[%s],是不是原始类型?[%s]\n",clazz.getName(),clazz.isPrimitive());
    }

}
