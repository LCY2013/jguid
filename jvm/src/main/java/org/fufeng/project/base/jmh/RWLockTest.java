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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 读写锁 实现计算平方
 * @create 2020-12-10
 */
public class RWLockTest {

    /**
     * 横纵坐标
     */
    private double x=0, y=0;

    /**
     * 读写锁定义
     */
    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 读锁
     */
    private final static Lock readLock = lock.readLock();

    /**
     * 写锁
     */
    private final static Lock writeLock = lock.writeLock();

    /**
     * 计算两个数的平方根
     *
     * @return 平方根
     */
    public double  read() {
        readLock.lock();
        try {
            System.out.println("read start lock");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read end lock");
            return Math.sqrt(x * x + y * y);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return 0;
    }

    /**
     * 移动二维坐标
     *
     * @param x 横轴
     * @param y 纵轴
     */
    public void move(double x, double y) {
        writeLock.lock();
        try {
            System.out.println("write start lock");
            this.x = x;
            this.y = y;
            TimeUnit.SECONDS.sleep(1);
            System.out.println("write end lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        final RWLockTest rwLockTest = new RWLockTest();
        run(()-> rwLockTest.move(1,2));
        run(()-> rwLockTest.move(1,2));
        run(()-> rwLockTest.move(1,2));
        run(rwLockTest::read);
        run(()-> rwLockTest.move(1,3));
        run(rwLockTest::read);
        run(rwLockTest::read);
        run(rwLockTest::read);
        run(()-> rwLockTest.move(1,3));
    }

    /**
     * 线程启动器
     *
     * @param runnable 线程执行逻辑
     */
    private static void run(Runnable runnable) {
        new Thread(runnable).start();
    }

}
