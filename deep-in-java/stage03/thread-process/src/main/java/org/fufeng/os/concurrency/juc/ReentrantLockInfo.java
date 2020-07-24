/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-24
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.concurrency.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: jguid
 * @description: {@link ReentrantLock} 重入锁
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-24
 * @see ReentrantLock
 */
public class ReentrantLockInfo {

    public static void main(String[] args) {
        // 操作锁方法
        //lockOpsMethods();

        // Lock 条件测试
        //conditionObject();

        // Lock timeout
        tryLockTimeout();
    }

    /**
     * 锁操作方法
     */
    private static void lockOpsMethods() {
        // 定义一个可重入锁
        ReentrantLock reentrantLock = new ReentrantLock();
        // 获取当前线程重入的次数
        final int holdCount = reentrantLock.getHoldCount();
        System.out.printf("Thread-[%s],在lock()之前已经重入的次数[%1d]\n",
                Thread.currentThread().getName(), holdCount);
        lock(reentrantLock, 100);
    }

    /**
     * 锁操作方法
     *
     * @param reentrantLock 可重入锁
     * @param times         调用锁的次数
     */
    private static void lock(ReentrantLock reentrantLock, int times) {
        // 条件判断
        if (times < 1) {
            return;
        }

        // 锁执行
        reentrantLock.lock();

        try {
            // times-- load
            lock(reentrantLock, --times);
            System.out.printf("Thread[%s],在第[%d]调用lock()方法后,重入的次数是[%d]\n",
                    Thread.currentThread().getName(), times++, reentrantLock.getHoldCount());
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * 测试锁条件设置
     */
    private static void conditionObject() {
        // 新建一个重入锁
        final Lock lock = new ReentrantLock();
        // 获取锁的条件变量,它也是作为通讯的媒介
        final Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("condition await start");
                // 类似于 Object#wait()
                condition.await();
                System.out.println("condition await end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("condition signal start");
                // 类似于 Object#notify()
                condition.signalAll();
                System.out.println("condition signal end");
            }finally {
                lock.unlock();
            }
        }).start();
    }

    /**
     *  测试锁超时
     */
    private static void tryLockTimeout(){
        // 新增一个Lock实现
        ReentrantLock lock = new ReentrantLock();

        // 定义两个线程进行演示
        runThread(lock);

        // 定义两个线程进行演示
        runThread(lock);
    }

    /**
     *   通过线程 进行锁lock
     * @param lock 锁对象
     */
    private static void runThread(ReentrantLock lock) {
        new Thread(()->{
            try {
                System.out.printf("Thread[%s],开始进入获取锁状态\n",Thread.currentThread().getName());
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.printf("Thread[%s],获取到锁状态\n", Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (InterruptedException e) {
                // 重制中断状态，防止中断状态被修改
                Thread.currentThread().interrupt();
                e.printStackTrace();
            } finally {
                // 如果锁被当前线程持有,那么就可以进行解锁操作
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }).start();
    }



}
