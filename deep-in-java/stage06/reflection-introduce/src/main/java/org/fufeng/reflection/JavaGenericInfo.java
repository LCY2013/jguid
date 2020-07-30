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
package org.fufeng.reflection;

import java.io.File;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: {@link Type}
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-29
 * @see Type
 */
public class JavaGenericInfo {

    public static void main(String[] args) throws ClassNotFoundException {
        // 模仿Spring 类扫描
        // 通过标准方式 - Java 反射
        // 通过ASM -
        // 获取扫描类
        Class<?> scanBaseClass = JavaGenericInfo.class;
        // 获取扫描路径
        // < jdk 9
        //String scanBasePackages = scanBaseClass.getPackage().getName();
        // ≥ jdk 9
        String scanBasePackages = scanBaseClass.getPackageName();

        // 类所在的class path 物理路径
        String classPath = getClassPath(scanBaseClass);
        // 获取class所在的物理文件目录
        File classPathDirectory = new File(classPath);
        // 获取扫描路径下的文件目录
        File scanBasePackageDirectory = new File(classPathDirectory,scanBasePackages.replace('.','/'));
        // 获取所有的class文件 -> *.class
        final File[] files = scanBasePackageDirectory.listFiles(file ->
                file.isFile() && file.getName().endsWith(".class"));
        System.out.println("class path : "+classPath);
        System.out.println("scan base package : "+scanBasePackages);
        System.out.println("scan class files : "+ Stream.of(files).map(File::getName).collect(Collectors.joining(" , ")));

        // 获取当前线程上下文的ClassLoader
        final ClassLoader classLoader =
                Thread.currentThread().getContextClassLoader();
        // 定义类加载器加载后的字节码对象
        List<Class<?>> targetClass = new LinkedList<>();
        for (File file : files){
            // 通过文件获取类的简单名称
            String simpleClassName = file.getName().substring(0,file.getName().indexOf("."));
            // 通过简单名称和包名称构造一个类名称
            String className = scanBasePackages + "." + simpleClassName;
            // 通过ClassLoader 加载所在类
            final Class<?> loadClass = classLoader.loadClass(className);
            // 判断该类是否标注有@Repository
            if (loadClass.isAnnotationPresent(Repository.class)){
                targetClass.add(loadClass);
            }
        }

        // 判断 targetClass 是否是CrudRepository 类的实现类
        targetClass.stream()
                .filter(JavaGenericInfo::isConcrete) //筛选具体类
                .filter(JavaGenericInfo::isCrudRepositoryType) // 筛选CrudRepository的实现类
                .forEach(type -> {  // CrudRepository 泛型类型
                    final Type[] genericInterfaces = type.getGenericInterfaces();// 获取泛型父接口
                    Stream.of(genericInterfaces)
                            .filter(t -> t instanceof ParameterizedType) // 判断泛型类型是否是ParameterizedType类型
                            .map(t -> (ParameterizedType)t)  // 转换成ParameterizedType
                            .filter(parameterizedType -> CrudRepository.class.equals(parameterizedType.getRawType())) // 判断parameterizedType原始类型是否是CrudRepository
                            .forEach(parameterizedType -> {
                                // 获取第一个泛型参数
                                final String typeName = parameterizedType.getActualTypeArguments()[0].getTypeName();
                                try {
                                    System.out.println(classLoader.loadClass(typeName));
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            });
                });
    }

    // 判断某个类类型是不是CrudRepository
    private static boolean isCrudRepositoryType(Class<?> type){
        return CrudRepository.class.isAssignableFrom(type);
    }

    // 判断某个类是不是具体类
    private static boolean isConcrete(Class<?> type){
        return !Modifier.isAbstract(type.getModifiers());
    }

    // 获取类所在目录
    private static String getClassPath(Class<?> type){
        /*return type.getProtectionDomain()
                .getCodeSource().getLocation()
                .getPath().substring(1);*/
        return type.getProtectionDomain()
                .getCodeSource().getLocation()
                .getPath();
    }
}

interface CrudRepository<E extends Serializable>{
    // declared method
}

@Repository
class UserRepository implements CrudRepository<User>, // getGenericInterfaces[0] = ParameterizedType -> ParameterizedType.rawType = CrudRepository
        // ParameterizedType.rawType = CrudRepository
        // ParameterizedType.getActualTypeArguments()[0] = User
        Comparable<UserRepository>, // getGenericInterfaces[1] = ParameterizedType ->
        // ParameterizedType.rawType = Comparable
        // ParameterizedType.getActualTypeArguments()[0] = UserRepository
        Serializable{ // getGenericInterfaces[2] = Class -> isInterface() == true


    @Override
    public int compareTo(UserRepository o) {
        return 0;
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Repository{

}

class User implements Serializable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
