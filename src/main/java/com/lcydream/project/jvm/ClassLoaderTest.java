package com.lcydream.project.jvm;

/**
 * @program: jguid
 * @description: java中类加载器的层次性，双亲委派以及沙箱原理？
 *  双亲委派就是实现沙箱安全的技术手段
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-14 18:42
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent());
        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println("----------------------");
        System.out.println(Object.class.getClassLoader());
    }
}
