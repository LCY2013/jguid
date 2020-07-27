/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-27
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: jguid
 * @description: Synchronization 同步示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-27
 */
public class SynchronizationInfo {

    // pthread_mutex_t lock
    static Lock lock = new ReentrantLock();
    // 定义一个计数器
    static volatile int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        // pthread_cond_t condition
        final Condition condition = lock.newCondition();

        // 前提: Lock#lock()
        // await() signal() signalAll()
        // Synchronized(Object)
        // wait() notify() notifyAll()

        synchronized (Object.class){
            //Object.class.wait();
        }

        Thread t1 = new Thread(SynchronizationInfo::addCounter);
        Thread t2 = new Thread(SynchronizationInfo::addCounter);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void addCounter(){
        // pthread_mutex_lock()
        lock.lock();

        // lock.tryLock()
        // pthread_mutex_tryLock()
        System.out.println(getThreadPrefix() + ",before counter : "+counter);
        counter++;
        System.out.println(getThreadPrefix() + ",after counter : "+counter);
        // pthread_mutex_unlock()
        lock.unlock();
    }

    private static String getThreadPrefix(){
        return String.format("ThreadName[%s]->ThreadId[%s]",
                Thread.currentThread().getName(),Thread.currentThread().getId());
    }

}
