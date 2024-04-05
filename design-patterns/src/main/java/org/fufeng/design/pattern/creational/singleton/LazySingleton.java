package org.fufeng.design.pattern.creational.singleton;

/**
 * 懒汉式
 * <p>
 * 问题：线程不安全
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:15
 */
public class LazySingleton {
    private static LazySingleton instance;
    // 防止反射破坏单例，通过反射可以破坏
    private static boolean isFirst = true;
    private LazySingleton() {
        if (isFirst) {
            isFirst = false;
        } else  {
            throw new RuntimeException("单例构造器禁止反射调用");
        }
    }

    //v1 线程安全问题
    /*public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }*/

    //v2
    /*public synchronized static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }*/

    //v3 == v2
    public static LazySingleton getInstance() {
        synchronized(LazySingleton.class) {
            if (instance == null) {
                instance = new LazySingleton();
            }
        }
        return instance;
    }
}
