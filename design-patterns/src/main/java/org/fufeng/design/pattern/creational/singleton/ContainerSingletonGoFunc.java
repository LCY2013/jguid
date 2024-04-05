package org.fufeng.design.pattern.creational.singleton;

/**
 * 线程执行
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:21
 */
public class ContainerSingletonGoFunc implements Runnable {

    @Override
    public void run() {
        ContainerSingleton.putInstance("object", new Object());
        Object object = ContainerSingleton.getInstance("object");
        System.out.println(Thread.currentThread().getName() + ":" + object);
    }

}
