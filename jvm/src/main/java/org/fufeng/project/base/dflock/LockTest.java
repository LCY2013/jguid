/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-12-11
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.project.base.dflock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 可重入锁 性能测试
 * <p>
 * LockTest执行时长：13552, count10000
 * @create 2020-12-11
 */
public class LockTest {

    private static int count;

    private static int readThreadNum = 0;
    private static int writeThreadNum = 1;

    private static int maxValue = 10000;
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Counter lockTest = new LockTest().new Counter();
        long startTime = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(readThreadNum + writeThreadNum);
        for (int i = 0; i < writeThreadNum; i++) {
            new Thread(() -> {
                for (int cur = 0; cur < maxValue; cur++) {
                    lockTest.count();
                }
                latch.countDown();
            }).start();
        }

        for (int i = 0; i < readThreadNum; i++) {
            new Thread(() -> {
                for (int cur = 0; cur < maxValue; cur++) {
                    lockTest.get();
                }
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("LockTest执行时长：" + (endTime - startTime) + ", count" + count);

    }

    class Counter {

        /**
         * 获取count值
         *
         * @return
         * @throws InterruptedException
         */
        public int get() {

            lock.lock();
            try {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return count;
                // System.out.println("count：" + count);
            } finally {
                lock.unlock();
            }
        }

        /**
         * count值+1
         *
         * @return
         * @throws InterruptedException
         */
        public void count() {
            lock.lock();
            try {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                count++;
                // System.out.println("count：" + count);
            } finally {
                lock.unlock();
            }
        }
    }

}
