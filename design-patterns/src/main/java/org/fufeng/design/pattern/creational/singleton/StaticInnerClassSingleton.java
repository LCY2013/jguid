package org.fufeng.design.pattern.creational.singleton;

/**
 * 单例模式-静态内部类
 * <p>
 * 基于类初始化的线程安全，jdk在Class对象的初始化锁住，保证线程安全
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:42
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {
        if (InnerClass.staticInnerClassSingleton != null) {
            throw new RuntimeException("单例构造器禁止反射调用");
        }
    }

    private static class InnerClass {
        private static StaticInnerClassSingleton staticInnerClassSingleton = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return InnerClass.staticInnerClassSingleton;
    }

}
