package com.lcydream.project.jguid.basics;

/**
 *  JDK11 具体看{@link ClassLoader}
 *
 *  jdk.internal.loader.ClassLoaders$AppClassLoader@2c13da15
 *  jdk.internal.loader.ClassLoaders$PlatformClassLoader@67b6d4ae
 *  null  -> 等同于Parent为 BootClassLoader
 */
public class ClassLoaderTest{

    public static void main(String[] args) {
        ClassLoaderClazz classLoaderClazz = new ClassLoaderClazz();
        try {
            classLoaderClazz.loadClass("com.lcydream.project.jguid.basics.ClassLoaderTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());

        ClassLoaderTest classLoaderTest = new ClassLoaderTest();
        System.out.println(classLoaderTest.getClass().getClassLoader());
        System.out.println(classLoaderTest.getClass().getClassLoader().getParent());
        System.out.println(classLoaderTest.getClass().getClassLoader().getParent().getParent());

    }

    static class ClassLoaderClazz extends ClassLoader{

    }

}
