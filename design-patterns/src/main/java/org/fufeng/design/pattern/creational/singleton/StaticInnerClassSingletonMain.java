package org.fufeng.design.pattern.creational.singleton;

/**
 * 单例模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:18
 */
public class StaticInnerClassSingletonMain {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new StaticInnerClassSingletonGoFunc());
        Thread t2 = new Thread(new StaticInnerClassSingletonGoFunc());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
