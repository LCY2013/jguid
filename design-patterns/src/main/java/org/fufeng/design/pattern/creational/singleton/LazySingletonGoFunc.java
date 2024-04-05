package org.fufeng.design.pattern.creational.singleton;

/**
 * 线程执行
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:21
 */
public class LazySingletonGoFunc implements Runnable {

    @Override
    public void run() {
        LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println(lazySingleton);
    }

}
