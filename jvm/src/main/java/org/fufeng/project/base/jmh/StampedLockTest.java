/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-12-10
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.project.base.jmh;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description StampedLock 锁应用
 * @create 2020-12-10
 */
public class StampedLockTest {

    /**
     * 横纵坐标
     */
    private volatile double x = 0, y = 0;

    /**
     * 优化有的读写锁
     */
    private final static StampedLock lock = new StampedLock();

    /**
     * 计算坐标点到原点的距离
     *
     * @return 坐标点到原点的距离
     */
    public double distanceFromOrigin() {
        // 获取乐观锁
        long readLock = lock.tryOptimisticRead();
        double curX = x, curY = y;
        try {
            // 判断读期间是否有写操作
            if (!lock.validate(readLock)) {
                // 升级为悲观读锁
                readLock = lock.readLock();
                System.out.println("read start lock");
                TimeUnit.SECONDS.sleep(1);
                curX = x;
                curY = y;
                System.out.println("read end lock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(readLock);
        }
        return Math.sqrt(curX * curX + curY * curY);
    }

    /**
     * 移动二维坐标
     *
     * @param x 横轴
     * @param y 纵轴
     */
    public void move(double x, double y) {
        // 获取写锁
        final long writeLock = lock.writeLock();
        try {
            System.out.println("write start lock");
            this.x += x;
            this.y += y;
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("write end lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放写锁
            lock.unlockWrite(writeLock);
        }
    }

    public static void main(String[] args) {
        final StampedLockTest stampedLockTest = new StampedLockTest();
        /*run(() -> stampedLockTest.move(1, 2));
        run(() -> stampedLockTest.move(1, 2));
        run(() -> stampedLockTest.move(1, 2));
        run(stampedLockTest::distanceFromOrigin);
        run(() -> stampedLockTest.move(1, 3));
        run(stampedLockTest::distanceFromOrigin);
        run(stampedLockTest::distanceFromOrigin);
        run(stampedLockTest::distanceFromOrigin);
        run(() -> stampedLockTest.move(1, 3));*/

        // 不可重入示例,导致死锁
        stampedLockTest.count();
    }

    /**
     * 线程启动器
     *
     * @param runnable 线程执行逻辑
     */
    private static void run(Runnable runnable) {
        new Thread(runnable).start();
    }



    // StampedLock 不可重入示例
    private int count = 0 ;

    public void count() {
        long stamp = lock.writeLock();
        try {
            count1();
            count++;
            System.out.println("count：" + count);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public void count1() {
        long stamp = lock.writeLock();
        try {
            count++;
            System.out.println("count：" + count);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

}
