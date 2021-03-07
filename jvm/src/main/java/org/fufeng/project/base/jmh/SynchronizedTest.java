/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-12-09
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

import java.util.concurrent.CountDownLatch;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description synchronized 测试用例代码
 * @create 2020-12-09
 */
public class SynchronizedTest {

    private static int count;

    private static int num = 1;

    private static int maxValue = 1000;

    private final static Object lockObj = new Object();

    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new SynchronizedTest().new Counter();
        final long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                for (int cur = 0; cur < maxValue; cur++) {
                    counter.add();
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.printf("LockTest 执行时长: %d , count: %d \n"
                , (System.currentTimeMillis() - startTime)
                , count);
    }

    class Counter {
        public void add() {
            synchronized (lockObj) {
                count++;
            }
        }
    }

}