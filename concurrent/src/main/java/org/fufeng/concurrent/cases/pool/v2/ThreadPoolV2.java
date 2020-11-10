/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-10
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.pool.v2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 线程池 第二版  线程池实现原理
 * 利用生产者、消费者模型实现线程池
 * @create 2020-11-10
 */
public class ThreadPoolV2 {

    /**
     * 定义一个队列用于缓存线程任务
     */
    private final BlockingQueue<Runnable> workQueue;

    /**
     * 自定义线程池
     */
    private final List<Thread> threadPool = new ArrayList<>();

    /**
     * 构建线程池
     *
     * @param core      核心线程数
     * @param workQueue 工作队列
     */
    public ThreadPoolV2(int core, BlockingQueue<Runnable> workQueue) {
        this(core, workQueue, null);
    }

    /**
     * 构建线程池
     *
     * @param core          核心线程
     * @param workQueue     工作队列
     * @param threadFactory 线程工厂
     */
    public ThreadPoolV2(int core, BlockingQueue<Runnable> workQueue,
                        ThreadFactoryV2 threadFactory) {
        this.workQueue = workQueue;
        ThreadFactoryV2 curThreadFactory = threadFactory == null ? new ThreadFactoryV2(workQueue) : threadFactory;
        // 初始化线程池中的线程
        for (int i = 0; i < core; i++) {
            final Thread thread = curThreadFactory.newThread();
            thread.start();
            threadPool.add(thread);
        }
    }

    /**
     * 提交任务
     *
     * @param runnable 任务
     */
    public void execute(Runnable runnable) {
        try {
            this.workQueue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待任务执行完成关闭线程池
     */
    public void close() {
        // 优先判断任务队列是否已经完成
        while (!this.workQueue.isEmpty()) {
            /*try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        for (Thread thread : this.threadPool) {
            thread.interrupt();
        }
        this.threadPool.clear();
    }

    /**
     * 即可关闭线程池
     */
    public void closeNow() {
        for (Thread thread : this.threadPool) {
            thread.interrupt();
        }
        this.threadPool.clear();
    }

    public static void main(String[] args) {
        ThreadPoolV2 threadPoolV2 = new ThreadPoolV2(3, new ArrayBlockingQueue<Runnable>(10));
        for (int i = 0; i < 10; i++) {
            threadPoolV2.execute(threadPoolV2::print);
        }
        //threadPoolV2.close();
        threadPoolV2.closeNow();
    }

    private void print() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.printf("[%s] - [%s] running \n", Thread.currentThread().getName(), sdf.format(new Date()));
    }
}
