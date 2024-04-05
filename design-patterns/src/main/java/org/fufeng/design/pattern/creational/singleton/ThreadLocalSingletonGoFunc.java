package org.fufeng.design.pattern.creational.singleton;

/**
 * 线程执行
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:21
 */
public class ThreadLocalSingletonGoFunc implements Runnable {

    @Override
    public void run() {
        ThreadLocalInstance instance = ThreadLocalInstance.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + instance);
    }

}
