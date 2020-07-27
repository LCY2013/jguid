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
package org.fufeng.os.concurrency.container;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: jguid
 * @description: {@link SynchronousQueue} 互斥队列
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-27
 * @see SynchronousQueue
 */
public class SynchronousQueueInfo {

    public static void main(String[] args) throws InterruptedException {
        // SynchronousQueue 互斥场景使用
        // SynchronousQueue put()使用后,其他put()需要在等take()之后
        // capacity == 0 , 又允许插入(put) 一个元素
        // offer 方法无效，add 方法抛出异常
        BlockingQueue<Integer> blockingQueue =
                new SynchronousQueue<>(true);
        // 创建一个线程执行器
        final ExecutorService executorService =
                Executors.newFixedThreadPool(2);

        for (AtomicInteger i = new AtomicInteger(1); i.get() < 100; i.incrementAndGet()) {
            // 写线程
            executorService.execute(() -> {
                // 必须要有 put，不能用 offer
                // BlockingQueue 尽可能用 put，避免使用 offer，最好不要用 add
                // sQueue.offer(1);
                // 如果 SynchronousQueue 被其他线程调用 take() 方法的话，会发生死锁
                try {
                    blockingQueue.put(i.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            // 读线程
            executorService.execute(() -> {
                try {
                    final Integer take = blockingQueue.take();
                    System.out.println(take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            TimeUnit.MILLISECONDS.sleep(100);
        }

        // 执行器等待一段时间
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        // 关闭线程执行器
        executorService.shutdown();

        //synchronousQueueVsArrayBlockingQueue();
    }

    private static void synchronousQueueVsArrayBlockingQueue(){
        // size == 0 特殊 ArrayBlockingQueue
        BlockingQueue<Integer> sQueue = new SynchronousQueue<Integer>();
        new Thread(()-> {
            try {
                sQueue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()-> {
            try {
                sQueue.take();
                //sQueue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println(sQueue.size());

        BlockingQueue<Integer> aQueue = new ArrayBlockingQueue<>(1);

        aQueue.add(1);

        System.out.println(aQueue.size());

    }

    // offer 方法来自于 Queue 接口，因此，子接口无法超越 Queue 方法签名
    public boolean equals(Object object)
//            throws Exception // 错误（编译时）：超越父类 Object equals(Object) 方法签名
            throws RuntimeException // 非 checked 异常时没有以上限制
    {

        return false;
    }

}
