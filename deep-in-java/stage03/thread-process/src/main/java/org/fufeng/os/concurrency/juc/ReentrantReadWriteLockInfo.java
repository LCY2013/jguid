/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-24
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.concurrency.juc;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: jguid
 * @description: {@link ReentrantReadWriteLock} 读写锁示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-24
 * @see ReentrantReadWriteLock
 */
public class ReentrantReadWriteLockInfo {

    private static Random random = new Random();

    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        // 获取读锁
        final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        runThread(readLock, writeLock);

        runThread(readLock, writeLock);
    }

    private static void runThread(ReentrantReadWriteLock.ReadLock readLock, ReentrantReadWriteLock.WriteLock writeLock) {
        new Thread(()->{
            if (random.nextInt() % 2 == 0){
                try {
                    readLock.lock();
                    System.out.printf("线程[%s]读锁执行成功\n",Thread.currentThread().getName());
                }finally {
                    readLock.unlock();
                    System.out.printf("线程[%s]读锁解开成功\n",Thread.currentThread().getName());
                }
            } else {
                try {
                    writeLock.lock();
                    System.out.printf("线程[%s]写锁执行成功\n",Thread.currentThread().getName());
                }finally {
                    writeLock.unlock();
                    System.out.printf("线程[%s]写锁解开成功\n",Thread.currentThread().getName());
                }
            }
        }).start();
    }

    /*
         结果集:
            第一种 ->
                线程[Thread-1]读锁执行成功
                线程[Thread-0]读锁执行成功
                线程[Thread-1]读锁解开成功
                线程[Thread-0]读锁解开成功
            第二种 ->
                线程[Thread-0]读锁执行成功
                线程[Thread-0]读锁解开成功
                线程[Thread-1]写锁执行成功
                线程[Thread-1]写锁解开成功
            第三种 ->
                线程[Thread-1]写锁执行成功
                线程[Thread-1]写锁解开成功
                线程[Thread-0]写锁执行成功
                线程[Thread-0]写锁解开成功
            第四种 ->
                线程[Thread-1]写锁执行成功
                线程[Thread-1]写锁解开成功
                线程[Thread-0]读锁执行成功
                线程[Thread-0]读锁解开成功
     */

}
