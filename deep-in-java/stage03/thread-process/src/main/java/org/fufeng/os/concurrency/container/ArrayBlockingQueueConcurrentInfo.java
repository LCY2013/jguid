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
package org.fufeng.os.concurrency.container;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: jguid
 * @description: {@link ArrayBlockingQueue} 阻塞队列
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-24
 * @see ArrayBlockingQueue
 */
public class ArrayBlockingQueueConcurrentInfo {

    public static void main(String[] args) throws InterruptedException {
        // 创建阻塞队列
        final ArrayBlockingQueue<Integer> blockingQueue =
                new ArrayBlockingQueue<>(1, true);

        // 用上面队列来实现消费者生产者
        // 创建线程池
        final ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 定义生产者
        executorService.execute(() -> {
            for (AtomicInteger atomicInteger = new AtomicInteger(1);
                 atomicInteger.get() < 100; atomicInteger.incrementAndGet()) {
                try {
                    // 隔5次休眠一下
                    if (atomicInteger.get() % 5 == 0){
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }
                    blockingQueue.put(atomicInteger.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 定义消费者
        executorService.execute(() -> {
            try {
                while (true) {
                    final Integer take = blockingQueue.take();
                    //System.out.println(take);
                    System.out.printf("Thread-[%s],[%d]\n",Thread.currentThread().getName(),take);
                    if (take == 99) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 定义消费者
        /*executorService.execute(() -> {
            try {
                while (true) {
                    final Integer take = blockingQueue.take();
                    System.out.printf("Thread-[%s],[%d]\n",Thread.currentThread().getName(),take);
                    if (take == 99) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/

        // 线程池等待时间
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        // 结束线程池
        executorService.shutdown();
    }

}
