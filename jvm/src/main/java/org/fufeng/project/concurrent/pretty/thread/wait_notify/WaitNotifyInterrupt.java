package org.fufeng.project.concurrent.pretty.thread.wait_notify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: WaitNotifyInterrupt
 * @author: LuoChunYun
 * @Date: 2019/5/18 11:20
 * @Description: 本例子说明wait是可以被中断的
 */
public class WaitNotifyInterrupt {

    private static Object object = new Object();

    public static void main(String[] args) {
        //创建线程池
        final ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            try {
                System.out.println("------begin-------");
                //开始阻塞等待
                synchronized (object) {
                    //object.wait() == object.wait(0)
                    object.wait();
                }
                System.out.println("------end-------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ScheduledThreadPoolExecutor scheduledExecutorService =
                new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.schedule(
                //这里去关掉线程池，实际是调用java.util.concurrent.ThreadPoolExecutor#interruptIdleWorkers
                ()-> {
                    System.out.println("interrupt begin ....");
                    executorService.shutdownNow();
                    System.out.println("interrupt end ....");
                },
                2000, TimeUnit.MILLISECONDS);
        scheduledExecutorService.shutdown();
    }
}
