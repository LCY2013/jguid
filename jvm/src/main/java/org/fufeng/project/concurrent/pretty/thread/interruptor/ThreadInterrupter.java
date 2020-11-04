package org.fufeng.project.concurrent.pretty.thread.interruptor;

import org.fufeng.project.concurrent.fatory.ThreadPoolFactory;

import java.util.concurrent.*;

/**
 * @ClassName: ThreadInterrupter
 * @author: LuoChunYun
 * @Date: 2019/5/18 22:01
 * @Description: interrupt 是代表线程的中断标志位，它不做中断操作，只是一个标识位
 *   {@link Thread#interrupt}  修改当前线程为中断状态
 *   {@link Thread#interrupted} 检测当前线程是否中断，并且清除中断标识
 *   {@link Thread#isInterrupted()} 检测当前线程是否中断，不清除中断标识
 */
public class ThreadInterrupter {

    private static Object object = new Object();

    public static void main(String[] args) {

        //interruptOne();
        //interruptTwo();
        //interruptThree();
        //interruptFour();
        interruptFive();

    }

    private static void interruptFive() {
        Thread thread = new Thread(()->{
            //判断这里的线程是否已经被中断了，如果被中断就返回true，重置中断标志为false
            for (;!Thread.interrupted();){

            }
            System.out.println("Thread has interrupted : " + Thread.currentThread().isInterrupted());
        });

        //启动子线程
        thread.start();

        //中断子线程
        thread.interrupt();

        //等待子线程执行完成
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread is over");
    }

    /**
     * isInterrupted:true
     * interrupted:false
     * isInterrupted:false
     * isInterrupted:true
     *
     * 这里可以看出子线程第一次true是没有疑问的，因为thread是main的子线程，所以他是可以修改子线程的中断标志
     *  而2,3,4行的输出就有点疑问？按正常逻辑应该是true，false，false
     *   但是这里第二行thread调用的{@link Thread#interrupted()} currentThread().isInterrupted(true)，
     *     实际该方法是主线程中执行的，所以interrupted()方法是主线程执行的
     */
    private static void interruptFour() {

        Thread thread = new Thread(()->{
            for (;;){

            }
        });

        //启动子线程
        thread.start();

        //中断子线程
        thread.interrupt();

        //获取中断标识
        System.out.println("isInterrupted:"+thread.isInterrupted());
        //获取中断标志并重置
        System.out.println("interrupted:"+thread.interrupted());
        //获取中断标志并重置
        System.out.println("isInterrupted:"+Thread.interrupted());
        //获取中断标志
        System.out.println("isInterrupted:"+thread.isInterrupted());

        //等待子线程执行完成
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread is over");
    }

    /**
     * 从这里可以看出sleep是可以被中断的
     */
    private static void interruptThree() {
        Thread thread = new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" sleep 5000 seconds");
                Thread.sleep(50000);
                System.out.println(Thread.currentThread().getName()+" awaking");
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+" is interrupted while sleeping");
                return;
            }
            System.out.println(Thread.currentThread().getName()+" leaving normally");
        });
        //启动子线程
        thread.start();

        //主线程休眠
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //子线程中断状态设置，让你从休眠状态恢复过来
        thread.interrupt();

        //等待子线程结束
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread is over");
    }

    private static void interruptTwo() {
        Thread thread = new Thread(()->{
            //判断线程是否被中断,中断就退出循环
            for (;!Thread.currentThread().isInterrupted();){
                System.out.println(Thread.currentThread().getName() + " doSomeThing");
            }
        });
        //启动子线程
        thread.start();

        //主线程休眠等待
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //开始中断子线程
        System.out.println("main thread interrupt subThread");
        thread.interrupt();

        //等待子线程执行完毕
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread over");
    }

    private static void interruptOne() {
        final ExecutorService executorService =
                Executors.newCachedThreadPool(new ThreadPoolFactory());
        executorService.execute(()->{
            //判断线程是否被中断,中断就退出循环
            for (;!Thread.currentThread().isInterrupted();){
                System.out.println(Thread.currentThread().getName() + " doSomeThing");
            }
        });
        final ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(()->{
            /**
             *  {@link ThreadPoolExecutor#interruptIdleWorkers(boolean)}
             * 实际调用的这里
             */
            System.out.println("main thread interrupt subThread");
            //实际调用的是线程的Interrupted
            executorService.shutdownNow();
            synchronized (object) {
                object.notify();
            }
        },10, TimeUnit.MILLISECONDS);

        scheduledExecutorService.shutdown();

        try {
            synchronized (object) {
                object.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread over");

    }


}
