/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-10-30
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 阻塞队列实现
 *
 * 阻塞队列有两个操作分别是入队和出队，这两个方法都是先获取互斥锁，类比管程模型中的入口。
 *
 * 1. 对于入队操作，如果队列已满，就需要等待直到队列不满，所以这里用了notFull.await()
 * 2. 对于出队操作，如果队列为空，就需要等待直到队列不空，所以就用了notEmpty.await()
 * 3. 如果入队成功，那么队列就不空了，就需要通知条件变量：队列不空notEmpty对应的等待队列
 * 4. 如果出队成功，那就队列就不满了，就需要通知条件变量：队列不满notFull对应的等待队列
 *
 * @create 2020-10-30
 */
public class BlockQueue<T> {

    /**
     *  定义重入锁
     */
    final Lock lock = new ReentrantLock();

    /**
     *  定义 队列未满 的条件变量
     */
    final Condition notFull = lock.newCondition();

    /**
     *  定义 队列未空 的条件变量
     */
    final Condition notEmpty = lock.newCondition();

    private Object[] arrays = new Object[8];

    private volatile int size = 0;

    /**
     *  入队列
     * @param x 入队列元素
     */
    void enq(T x){
        lock.lock();
        try {
            // 队列已经满
            while (arrays.length == size){
                // 等待队列未满
                notFull.await();
            }
            arrays[size] = x;
            size++;
            // 入对后通知队列未空
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     *  出队列
     * @param x 出队列元素
     */
    T deq(T x){
        lock.lock();
        try {
            // 队列已空
            while (size==0){
                notEmpty.await();
            }

            // 出队列
            T ret = (T)arrays[0];
            if (size != 1) {
                Object[] newArrays = new Object[size];
                System.arraycopy(arrays, 1, newArrays, 0, size - 1);
                arrays = newArrays;
            }
            size--;
            notFull.signalAll();
            return ret;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
}
