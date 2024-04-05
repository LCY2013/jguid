package org.fufeng.design.pattern.creational.singleton;

/**
 * 容器模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:18
 */
public class ThreadLocalSingletonMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ThreadLocalSingleton: "+ThreadLocalInstance.getInstance());
        System.out.println("ThreadLocalSingleton: "+ThreadLocalInstance.getInstance());
        System.out.println("ThreadLocalSingleton: "+ThreadLocalInstance.getInstance());
        System.out.println("ThreadLocalSingleton: "+ThreadLocalInstance.getInstance());
        Thread t1 = new Thread(new ThreadLocalSingletonGoFunc());
        Thread t2 = new Thread(new ThreadLocalSingletonGoFunc());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
