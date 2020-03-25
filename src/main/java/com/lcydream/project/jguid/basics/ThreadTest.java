package com.lcydream.project.jguid.basics;

import java.util.Date;
import java.util.concurrent.*;

public class ThreadTest {

    private static int corePoolSize = 5;
    private static int maximumPoolSize = 100;
    private static long keepAliveTime = 5;
    private static TimeUnit unit = TimeUnit.SECONDS;
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue(100);
    private static ThreadFactory threadFactory = runnable -> new Thread(runnable,"ThreadTest");
    private static RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );
        for(int i=0;i<10;i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName()+
                        " thread starting " + new Date());
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+
                        " thread end " + new Date());
            });
        }
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()){

        }
    }

}
