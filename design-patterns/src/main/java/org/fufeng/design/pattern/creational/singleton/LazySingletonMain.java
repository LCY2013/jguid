package org.fufeng.design.pattern.creational.singleton;

/**
 * 单例模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:18
 */
public class LazySingletonMain {

    public static void main(String[] args) throws InterruptedException {
        // 懒汉单例
        /*LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println(lazySingleton);*/
        Thread t1 = new Thread(new LazySingletonGoFunc());
        Thread t2 = new Thread(new LazySingletonGoFunc());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
