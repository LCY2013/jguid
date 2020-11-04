package org.fufeng.project.concurrent.pretty.thread.wait_notify;

import org.fufeng.project.concurrent.fatory.ThreadPoolFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: TheadDiffrentResourcesNotify
 * @author: LuoChunYun
 * @Date: 2019/5/18 17:02
 * @Description: TODO
 */
public class TheadDiffrentResourcesNotify {

    /**
     * 创建共享资源
     */
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {

        //创建线程池
        final ExecutorService executorService = Executors.newCachedThreadPool(new ThreadPoolFactory());
        executorService.execute(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+" get resourceA lock");
                //获取资源A上的监视器
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread().getName()+" begin wait");
                    //阻塞线程A，释放掉资源A的监视器
                    resourceA.wait();
                    System.out.println(Thread.currentThread().getName()+" end wait");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+" release resourceA lock");
                //获取资源A上的监视器
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread().getName()+" begin wait");
                    //阻塞线程A，释放掉资源A的监视器
                    resourceA.wait();
                    System.out.println(Thread.currentThread().getName()+"end wait");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                //保证Thread C 在最后执行
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" get resourceA lock");
            //获取资源A上的监视器
            synchronized (resourceA) {
                //阻塞线程A，释放掉资源A的监视器
                System.out.println(Thread.currentThread().getName()+" begin notify");
                //resourceA.notify(); 这里只会唤醒一个唤醒，这里的例子阻塞等待了两个，所以程序会一直阻塞，可以将其换成notifyAll
                resourceA.notifyAll();
                System.out.println(Thread.currentThread().getName()+" end notify");
            }
        });

        //关闭线程池
        executorService.shutdown();
    }
}
