/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-08-03
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.java.reflection;

import java.io.File;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: 泛型详解
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-03
 */
public class JavaGenericInfo {

    public static void main(String[] args) throws ClassNotFoundException {
        // 模拟Spring 类扫描
        // 通过标准实现 - Java 反射
        // 通过ASM -
        Class<?> scanBaseClassPackage = JavaGenericInfo.class;
        // 获取当前扫描类的包名
        final String packageName =
                scanBaseClassPackage.getPackage().getName();
        // 获取扫描类所在的物理路径
        final String classPath = getClassPath(scanBaseClassPackage);
        final File classPathDirectory = new File(classPath);
        // 扫描包的物理路径
        final File scanBasePackageDirectory = new File(classPathDirectory,
                packageName.replace(".", File.separator));
        // 获取所有的class文件 -> *.class
        final File[] listClassFiles = scanBasePackageDirectory.listFiles(
                file -> file.isFile() && file.getName().endsWith(".class"));
        System.out.println("class path:"+classPath);
        System.out.println("scan base package:"+packageName);
        System.out.println("scan class files:"+ Stream.of(listClassFiles)
                .map(File::getName).collect(Collectors.joining(",")));

        // 获取当前线程的类加载器
        final ClassLoader contextClassLoader =
                Thread.currentThread().getContextClassLoader();
        // 通过类加载器加载读取的字节码文件
        List<Class<?>> targetClasses = new LinkedList<>();
        for (File classFile : listClassFiles){
            // 获取简单类名称
            final String simpleClassName =
                    classFile.getName().substring(0, classFile.getName().lastIndexOf("."));
            String className = packageName + "." + simpleClassName;
            // 加载所有类
            final Class<?> loadClass = contextClassLoader.loadClass(className);
            // 判断是否具有repository注解
            if (loadClass.isAnnotationPresent(Repository.class)){
                targetClasses.add(loadClass);
            }
        }

        // targetClass 是否是CurdRepository类的实现类
        targetClasses.stream()
                .filter(JavaGenericInfo::isConcrete) // 筛选出具体类
                .filter(JavaGenericInfo::isCurdRepository) // 筛选出CurdRepository类及其子类
                .forEach(type -> {  // CurdRepository泛型类型
                    // 获取泛型父接口
                    final Type[] genericInterfaces = type.getGenericInterfaces();
                    // 判断泛型父接口是否符合条件
                    Stream.of(genericInterfaces)
                            .filter(t -> t instanceof ParameterizedType) // 判断泛型接口是否是ParameterizedType
                            .map(t -> (ParameterizedType)t) // 将泛型接口转换成ParameterizedType类型
                            .filter(parameterizedType ->  // 筛选原始类型是否是CurdRepository
                                    CurdRepository.class.equals(parameterizedType.getRawType()))
                            .forEach(parameterizedType -> {
                                // 获取第一个泛型参数
                                final String typeName = parameterizedType
                                        .getActualTypeArguments()[0].getTypeName();
                                try {
                                    System.out.println(contextClassLoader.loadClass(typeName));
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            });
                });

    }

    /**
     *  判断某个类型是否是具体类型(即不是抽象类型)
     * @param type 某个类型
     * @return 是否是具体类型
     */
    private static boolean isConcrete(Class<?> type){
        return !Modifier.isAbstract(type.getModifiers());
    }

    /**
     *  是否是CurdRepository 类型或者它的子类型
     * @param type 字节码class对象
     * @return 是否是CurdRepository 类型或者它的子类型
     */
    private static boolean isCurdRepository(Class<?> type){
        return CurdRepository.class.isAssignableFrom(type);
    }

    /**
     *  获取扫描类路径
     * @param scanBaseClassPackage 扫描类字节码
     * @return 扫描类所在路径
     */
    private static String getClassPath(Class<?> scanBaseClassPackage) {
        return scanBaseClassPackage.getProtectionDomain()
                //.getCodeSource().getLocation().getPath().substring(1);  windows 平台
                .getCodeSource().getLocation().getPath();  // unix 或者 linux 平台
    }

}
