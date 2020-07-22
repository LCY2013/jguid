/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-22
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.thread;

/**
 * @program: jguid
 * @description: 并发核心原语方法 wait() 与 notify()
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-22
 * @see Thread
 */
public class ThreadWaitAndNotifyInfo {

    public static void main(String[] args) {
        // Thread 实现 Runnable
        // 如果没有传递 Runnable 对象实现，空执行
        // wait() 语义：在同步（互斥）场景下

        /*
          t1 -> 需要monitor
          t2 -> 需要monitor
          如果 t1 先拿到monitor，它在wait()后就会释放掉monitor，然后进入等待队列，
          而 t2 在 t1 释放monitor后也会拿到monitor，然后wait()后也会释放掉monitor
          所以下面的两个线程都会被阻塞
         */
        // 线程一
        Thread t1 = new Thread(ThreadWaitAndNotifyInfo::say);
        t1.setName("t1");
        t1.start();

        // 线程二
        Thread t2 = new Thread(ThreadWaitAndNotifyInfo::say);
        t2.setName("t2");
        t2.start();

        // 这里可以比较一下wait()与join()方法，这两个方法看起来是效果是一样的，因为join()本身也是调用了wait(0)
        Object monitor = ThreadWaitAndNotifyInfo.class;

        // 这里调用notify的必须是同一个对象，由于是对monitor上的wait(),所以需要使用monitor的notify
        /*synchronized (monitor){
            t1.notify();
            t2.notify();
        }*/
        synchronized (monitor) {
            monitor.notifyAll();
        }

        /*
            第三方可以(条件)控制 t1 或者 t2 释放
            > jdk1.5 : Lock 加入了 Condition 条件,await()等待与signal()唤醒
         */
    }

    private static void say() {
        // 获取当前线程
        Thread thread = Thread.currentThread();
        // 设置当前字节码对象为锁对象
        Object monitor = ThreadWaitAndNotifyInfo.class;

        synchronized (monitor) {
            try {
                System.out.printf("线程[%s],进入wait等待状态\n", thread.getName());
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("线程[%s],进入恢复执行状态\n", thread.getName());
            System.out.printf("线程[%s],say magic!\n", thread.getName());
        }
    }
}
