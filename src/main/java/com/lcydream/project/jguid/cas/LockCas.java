package com.lcydream.project.jguid.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * jdk 提供的cas锁,实现自定义锁
 */
public class LockCas {

    private volatile long lock = 0;

    private static int total;

    //jdk1.9之后
    private static final AtomicLongFieldUpdater<LockCas> lockFieldUpdater =
            AtomicLongFieldUpdater.newUpdater(LockCas.class,"lock");

    public void acquireLock(){
        long t = Thread.currentThread().getId();
        while (!lockFieldUpdater.compareAndSet(this,0,t)){
            //等待一会儿，防止cpu过度使用
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void unLock(){
        long t = Thread.currentThread().getId();
        lockFieldUpdater.compareAndSet(this,t,0);
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        LockCas lockCas = new LockCas();
        new Thread(()->lockCas.print(10000,countDownLatch)).start();
        new Thread(()->lockCas.print(10000,countDownLatch)).start();
        new Thread(()->lockCas.print(10000,countDownLatch)).start();
        new Thread(()->lockCas.print(10000,countDownLatch)).start();
        new Thread(()->lockCas.print(10000,countDownLatch)).start();
        new Thread(()->lockCas.print(10000,countDownLatch)).start();
        new Thread(()->lockCas.print(10000,countDownLatch)).start();
        new Thread(()->lockCas.print(10000,countDownLatch)).start();
        new Thread(()->lockCas.print(10000,countDownLatch)).start();
        new Thread(()->lockCas.print(10000,countDownLatch)).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void print(int i,CountDownLatch countDownLatch){
        acquireLock();
        try {
            for (;i>0;i--){
                total += i;
                try {
                    if(i % 100 == 0) {
                        TimeUnit.MILLISECONDS.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("-------"+total);
            countDownLatch.countDown();
        }finally {
            unLock();
        }
    }
}
