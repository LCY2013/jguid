package org.fufeng.design.pattern.creational.singleton;

/**
 * 懒汉式双重检查
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:15
 */
public class LazyDoubleCheckSingleton {
    //v1
    //private static LazyDoubleCheckSingleton instance;
    //v2
    private volatile static LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton() {
    }

    public static LazyDoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new LazyDoubleCheckSingleton();
                    // 1、分配内存给这个对象
                    // 2、初始化对象
                    // 3、设置instance指向刚分配的内存，此时instance已经不是null了

                    //问题：指令重排序，可能导致instance对象还没有初始化完成，就被使用了，intra-thread semantics
                    // 1、分配内存给这个对象
                    // 3、设置instance指向刚分配的内存，此时instance已经不是null了
                    // 2、初始化对象

                    //解决办法：volatile
                }
            }
        }

        return instance;
    }
}
