//package com.lcydream.project.jguid.cas;
//
//import java.lang.invoke.MethodHandles;
//import java.lang.invoke.VarHandle;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
///**
// * 利用varHandler实现锁
// */
//public class LockVarHandler {
//
//    public volatile long lock = 0;
//
//    private static int total;
//
//    /**
//     * jdk1.9后新增
//     */
//    private static VarHandle HANDLE = null;
//
//    static {
//        try {
//            HANDLE = MethodHandles.lookup()
//                        .findVarHandle(LockVarHandler.class,"lock",long.class);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void acquireLock(){
//        long t  = Thread.currentThread().getId();
//        while (!HANDLE.compareAndSet(this,0,t)){
//            try {
//                TimeUnit.MILLISECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void unLock(){
//        long t  = Thread.currentThread().getId();
//        HANDLE.compareAndSet(this,t,0);
//    }
//
//    public static void main(String[] args) {
//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        LockVarHandler lockVarHandler = new LockVarHandler();
//        new Thread(()->lockVarHandler.print(10000,countDownLatch)).start();
//        new Thread(()->lockVarHandler.print(10000,countDownLatch)).start();
//        new Thread(()->lockVarHandler.print(10000,countDownLatch)).start();
//        new Thread(()->lockVarHandler.print(10000,countDownLatch)).start();
//        new Thread(()->lockVarHandler.print(10000,countDownLatch)).start();
//        new Thread(()->lockVarHandler.print(10000,countDownLatch)).start();
//        new Thread(()->lockVarHandler.print(10000,countDownLatch)).start();
//        new Thread(()->lockVarHandler.print(10000,countDownLatch)).start();
//        new Thread(()->lockVarHandler.print(10000,countDownLatch)).start();
//        new Thread(()->lockVarHandler.print(10000,countDownLatch)).start();
//
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void print(int i,CountDownLatch countDownLatch){
//        acquireLock();
//        try {
//            for (;i>0;i--){
//                total += i;
//                try {
//                    if(i % 100 == 0) {
//                        TimeUnit.MILLISECONDS.sleep(1);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("-------"+total);
//            countDownLatch.countDown();
//        }finally {
//            unLock();
//        }
//    }
//
//}
