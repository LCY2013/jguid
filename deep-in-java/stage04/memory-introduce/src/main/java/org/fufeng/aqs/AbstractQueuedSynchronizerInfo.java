/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-28
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.aqs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: jguid
 * @description: {@link AbstractQueuedSynchronizer} 解析
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-28
 * @see AbstractQueuedSynchronizer AQS
 */
public class AbstractQueuedSynchronizerInfo {

    // 定义一个可重入锁
    // AbstractQueuedSynchronizer 同步队列去实现的公平与非公平特性
    // ReentrantLock is AbstractQueuedSynchronizer
    private static final Lock lock = new ReentrantLock(true);

    // 定义一个条件量
    // Condition is a part of ReentrantLock or AbstractQueuedSynchronizer
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程执行队列
        final ExecutorService executorService = Executors.newFixedThreadPool(3);

        // main -> thread-1.interrupt()
        // 定义集合所以线程的集合对象
        List<Thread> threads = new ArrayList<>();
        executorService.submit(()->{
            threads.add(Thread.currentThread());
            action();
        }); // thread-1
        executorService.submit(AbstractQueuedSynchronizerInfo::action); // thread-1
        executorService.submit(AbstractQueuedSynchronizerInfo::action); // thread-2
        executorService.submit(AbstractQueuedSynchronizerInfo::action); // thread-3

        // 非公平锁
        // thread-1 unlock -> release -> unpark thread-2 -> thread-2 try acquire
        // thread-4 or thread-5 lock -> try acquire

        // PS : unpark = LockSupport.unpark

        // 公平锁
        // thread-1 unlock -> release -> unpark thread-2 -> thread-2 try acquire
        // thread-2 lock -> ..
        // thread-3 wait
        // thread-4 wait
        // thread-5 wait

        // 线程执行器定义一个销毁时间
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        // 线程执行器销毁
        executorService.shutdown();
    }

    /**
     *  执行动作
     */
    private static void action() {
        //System.out.printf("concurrent Thread[%s],starting...\n",
        //        Thread.currentThread().getName());
        // I/O线程中断
        try {
            // 上锁
            lock.lock();
            System.out.printf("concurrent Thread[%s],starting...\n",
                    Thread.currentThread().getName());
            System.in.read();
            System.out.printf("concurrent Thread[%s],ended\n",
                    Thread.currentThread().getName());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
