/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-06
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description StampedLock 支持三种模式，分别是：写锁、悲观读锁和乐观读。
 * <p>
 * 与ReadWriteLock异同？
 * 相同点：写锁、悲观读锁的语义和 ReadWriteLock的写锁、读锁的语义非常类似，允许多个线程同时获取悲观读锁，但是只允许一个线程获取写锁，写锁和悲观读锁是互斥的。
 * <p>
 * 不同点：StampedLock 里的写锁和悲观读锁加锁成功之后，都会返回一个 stamp，然后解锁的时候，需要传入这个 stamp。
 * <p>
 * 注意点：
 * StampedLock 不支持重入，悲观读锁、写锁都不支持条件变量。
 * <p>
 * 如果线程阻塞在 StampedLock 的 readLock() 或者writeLock() 上时，此时调用该阻塞线程的 interrupt() 方法，会导致 CPU 飙升。
 * <p>
 * 使用 StampedLock 一定不要调用中断操作，如果需要支持中断功能，一定使用可中断的悲观读锁 readLockInterruptibly() 和写锁 writeLockInterruptibly()。
 * @create 2020-11-06
 * @see StampedLock
 */
public class StampedLockCase {

    /**
     * 演示 {@link StampedLock}
     */
    public void case01() {
        final StampedLock stampedLock = new StampedLock();

        // 读锁示例
        final long readLock = stampedLock.readLock();
        try {
            System.out.println("read logic ...");
        } finally {
            stampedLock.unlockRead(readLock);
        }

        // 写锁示例
        final long writeLock = stampedLock.writeLock();
        try {
            System.out.println("write logic ...");
        } finally {
            stampedLock.unlockWrite(writeLock);
        }
    }

    /**
     * 线程 T1 获取写锁之后将自己阻塞，线程 T2 尝试获取悲观读锁，也会阻塞；
     * 如果此时调用线程 T2 的 interrupt() 方法来中断线程 T2 的话，你会发现线程 T2 所在 CPU会飙升到 100%
     */
    public void case02() throws InterruptedException {
        final StampedLock stampedLock = new StampedLock();

        // 定义线程thread1操作
        final Thread thread1 = new Thread(() -> {
            // 获取写锁
            stampedLock.writeLock();
            // 永久阻塞在这里
            LockSupport.park();
        });
        // 启动thread1线程
        thread1.start();
        // 主线程休眠等待thread1线程获取到写锁
        TimeUnit.MILLISECONDS.sleep(200);

        // 定义线程thread2操作
        final Thread thread2 = new Thread(() -> {
            final long readLock = stampedLock.readLock();
            try {
                System.out.println("success get write lock");
            } finally {
                stampedLock.unlockRead(readLock);
            }
        });
        // 启动thread2线程
        thread2.start();
        // 主线程休眠等待thread2线程获取到写锁
        TimeUnit.MILLISECONDS.sleep(200);

        // 中断thread2
        thread2.interrupt();
        // 等待thread2
        thread2.join();
    }

    public static void main(String[] args) throws InterruptedException {
        /*final StampedLockCase stampedLockCase = new StampedLockCase();
        stampedLockCase.case01();*/

        /*final Point point = new Point();
        point.x = 3;
        point.y = 4;
        new Thread(()->point.set(5,6)).start();
        System.out.println((point.distanceFromOrigin()));*/

        final StampedLockCase stampedLockCase = new StampedLockCase();
        stampedLockCase.case02();
    }

}
