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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: jguid
 * @description: {@link CountDownLatch} 等待走完
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-24
 * @see CountDownLatch
 */
public class CountDownLatchInfo {

    public static void main(String[] args) throws InterruptedException {
        // 新建一个CountDownLatch 对象
        CountDownLatch countDownLatch = new CountDownLatch(5);
        // 创建一个线程池去执行任务
        final ExecutorService executorService =
                Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            // 方法引用
            executorService.execute(() -> {
                CountDownLatchInfo.print();
                countDownLatch.countDown();
            });
        }

        // 主线程休眠等待上面线程执行
        TimeUnit.SECONDS.sleep(1);

        // 查看当前的计数器数量
        System.out.printf("CountDownLatch 剩余数量[%d]\n",countDownLatch.getCount());
        // CountDownLatch 阻塞,里面是一个for(;;)循环，如果没有到执行链表不为空，就会一直循环
        countDownLatch.await();

        // 关闭线程池
        executorService.shutdown();
    }

    private static void print() {
        System.out.printf("Thread-[%s],running...\n",
                Thread.currentThread().getName());
    }
}
