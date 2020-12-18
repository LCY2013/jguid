/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-12-18
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.project.base.thread.context;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 线程上下文切换，单线程多线程性能测试
 * @create 2020-12-18
 */
public class ThreadContentSwitch {

    public static void main(String[] args) {
        // 运行多线程
        MultiThreadTester test1 = new MultiThreadTester();
        test1.Start();
        // 运行单线程
        SerialTester test2 = new SerialTester();
        test2.Start();
    }

    static class MultiThreadTester extends ThreadContextSwitchTester {
        @Override
        public void Start() {
            long start = System.currentTimeMillis();
            MyRunnable myRunnable = new MyRunnable();
            Thread[] threads = new Thread[4];
            // 创建多个线程
            for (int i = 0; i < 4; i++) {
                threads[i] = new Thread(myRunnable);
                threads[i].start();
            }
            for (int i = 0; i < 4; i++) {
                try {
                    // 等待一起运行完
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("multi thread start time : " + (System.currentTimeMillis() - start));
            System.out.println("counter: " + counter);
        }

        // 创建一个实现 Runnable 的类
        class MyRunnable implements Runnable {
            public void run() {
                while (counter < 100000000)
                    synchronized (this) {
                        if (counter < 100000000) {
                            increaseCounter();
                        }
                    }
            }
        }
    }

    // 创建一个单线程
    static class SerialTester extends ThreadContextSwitchTester {
        @Override
        public void Start() {
            long start = System.currentTimeMillis();
            for (long i = 0; i < count; i++) {
                increaseCounter();
            }
            long end = System.currentTimeMillis();
            System.out.println("serial exec time : " +(end - start));
            System.out.println("counter: " + counter);
        }
    }


    // 父类
    static abstract class ThreadContextSwitchTester {
        public static final int count = 100000000;
        public volatile int counter = 0;

        public int getCount() {
            return this.counter;
        }

        public void increaseCounter() {
            this.counter += 1;
        }

        public abstract void Start();
    }


}
