package org.fufeng.project.concurrent.pretty.thread.sleep;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: SleepCatInfo
 * @author: LuoChunYun
 * @Date: 2019/5/18 17:59
 * @Description: TODO
 */
public class SleepCatInfo {

    /**
     * 独占锁/重入锁
     */
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        //sleepLock();
        sleepLockInterrupt();

        //sleep设置休眠时间方法不允许出现负数
        //Thread.sleep(-1);
    }

    /**
     threadA is in sleep
         java.lang.InterruptedException: sleep interrupted
         at java.lang.Thread.sleep(Native Method)
         at org.fufeng.project.concurrent.pretty.thread.sleep.SleepCatInfo.lambda$sleepLockInterrupt$0(SleepCatInfo.java:36)
         at java.lang.Thread.run(Thread.java:748)
     threadB is in sleep
     threadB is in awaked
     结果表明sleep是可以被中断的
     */
    private static void sleepLockInterrupt(){
        //创建线程A
        Thread threadA = new Thread(()->{
            try {
                //获取独占锁
                lock.lock();

                System.out.println("threadA is in sleep");
                Thread.sleep(5000);
                System.out.println("threadA is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        //创建线程A
        Thread threadB = new Thread(()->{
            try {
                //获取独占锁
                lock.lock();

                System.out.println("threadB is in sleep");
                Thread.sleep(5000);
                System.out.println("threadB is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        threadA.start();
        threadB.start();

        try {
            //主线程休眠
            TimeUnit.SECONDS.sleep(1);
            //线程A中断
            threadA.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *  threadA is in sleep
     *  threadA is in awaked
     *  threadB is in sleep
     *  threadB is in awaked
     *
     *  sleep只是让出线程执行权，但是不会像wait()一样释放掉监视锁
     */
    private static void sleepLock(){

        //创建线程A
        Thread threadA = new Thread(()->{
            try {
                //获取独占锁
                lock.lock();

                System.out.println("threadA is in sleep");
                Thread.sleep(2000);
                System.out.println("threadA is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        //创建线程A
        Thread threadB = new Thread(()->{
            try {
                //获取独占锁
                lock.lock();

                System.out.println("threadB is in sleep");
                Thread.sleep(2000);
                System.out.println("threadB is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        threadA.start();
        threadB.start();


    }
}
