package org.fufeng.design.pattern.creational.singleton;

/**
 * 线程执行
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:21
 */
public class LazyDoubleCheckSingletonGoFunc implements Runnable {

    @Override
    public void run() {
        LazyDoubleCheckSingleton lazySingleton = LazyDoubleCheckSingleton.getInstance();
        System.out.println(lazySingleton);
    }

}
