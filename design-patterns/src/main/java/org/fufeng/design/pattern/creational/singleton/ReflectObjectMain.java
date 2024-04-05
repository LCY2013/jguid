package org.fufeng.design.pattern.creational.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 反射测试破坏单例模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:59
 */
public class ReflectObjectMain {

    public static void main(String[] args) {
        // 懒汉模式
        /*Class<HungrySingleton> clazz = HungrySingleton.class;
        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        try {
            HungrySingleton instance = (HungrySingleton) constructor.newInstance();
            HungrySingleton instance1 = HungrySingleton.getInstance();
            System.out.println(instance);
            System.out.println(instance1);
            System.out.println(instance == instance1);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // 静态内部类
        /*Class<StaticInnerClassSingleton> clazz = StaticInnerClassSingleton.class;
        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        try {
            StaticInnerClassSingleton instance = (StaticInnerClassSingleton) constructor.newInstance();
            StaticInnerClassSingleton instance1 = StaticInnerClassSingleton.getInstance();
            System.out.println(instance);
            System.out.println(instance1);
            System.out.println(instance == instance1);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // 懒汉模式
        /*Class<LazySingleton> clazz = LazySingleton.class;
        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        try {
            LazySingleton instance = (LazySingleton) constructor.newInstance();
            LazySingleton instance1 = LazySingleton.getInstance();
            System.out.println(instance);
            System.out.println(instance1);
            System.out.println(instance == instance1);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // 懒汉模式绕过单例检测
        Class<LazySingleton> clazz = LazySingleton.class;
        try {
            Constructor<LazySingleton> declaredConstructor = clazz.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            LazySingleton instance = declaredConstructor.newInstance();
            Field isFirst = clazz.getDeclaredField("isFirst");
            isFirst.setAccessible(true);
            isFirst.set(instance, true);
            LazySingleton instance1 = LazySingleton.getInstance();
            System.out.println(instance);
            System.out.println(instance1);
            System.out.println(instance == instance1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
