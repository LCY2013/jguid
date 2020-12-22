/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-12-22
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.coroutine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 模拟线程高度碰撞情况
 * <p>
 * 子线程执行时长：1596
 * @create 2020-12-22
 */
public class ThreadRun {

    private static Integer count = 0;
    private final static Integer FULL = 10;
    private final static Object LOCK = new Object();

    public static void main(String[] args) {
        final long start = System.currentTimeMillis();

        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Producer());
            thread.start();
            list.add(thread);
        }

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Consumer());
            thread.start();
            list.add(thread);
        }

        try {
            for (Thread thread : list) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("子线程执行时长：" + (end - start));
    }

    static class Producer implements Runnable {
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (count.equals(FULL)) {
                        try {
                            LOCK.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (Exception ignored) {
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

}
