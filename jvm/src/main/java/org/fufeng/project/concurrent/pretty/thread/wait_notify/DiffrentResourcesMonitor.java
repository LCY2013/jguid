package org.fufeng.project.concurrent.pretty.thread.wait_notify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: DiffrentResourcesMonitor
 * @author: LuoChunYun
 * @Date: 2019/5/18 11:08
 * @Description:
 *   这个例子说明了一个线程可以获取多个对象锁，
 *   但是一个对象锁的释放不会释放掉其他对象锁,
 *   并且wait()是可以被中断的
 */
public class DiffrentResourcesMonitor {

    /**
     * 创建共享资源
     */
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {
        //创建线程池
        final ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(()->{
            try {
                //获取资源A上的监视器
                synchronized (resourceA){
                    System.out.println("thread A get resourceA lock");
                    //获取资源B上的监视器
                    synchronized (resourceB){
                        System.out.println("thread A get resourceB lock");
                        //阻塞线程A，释放掉资源A的监视器
                        System.out.println("thread A release resourceA lock");
                        resourceA.wait();
                    }
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                //获取资源A上的监视器
                synchronized (resourceA){
                    System.out.println("thread B get resourceA lock");
                    //获取资源B上的监视器
                    synchronized (resourceB){
                        System.out.println("thread B get resourceB lock");
                        //阻塞线程A，释放掉资源A的监视器
                        System.out.println("thread B release resourceA lock");
                        resourceA.wait();
                    }
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        //关闭线程池
        executorService.shutdown();
    }
}
