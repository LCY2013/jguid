package org.fufeng.design.pattern.creational.singleton;

/**
 * 单例模式-double check
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:18
 */
public class LazyDoubleCheckSingletonMain {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new LazyDoubleCheckSingletonGoFunc());
        Thread t2 = new Thread(new LazyDoubleCheckSingletonGoFunc());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
