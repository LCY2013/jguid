/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-13
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.workerthread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 模拟在同一个线程池中死锁情况
 * 原因在于同一个线程池中存在多个阶段的任务提交，但是任务直接又有依赖关系，造成死锁问题
 *
 *  可以看到threaddump.txt中，线程池中的线程pool-1-thread-1 pool-1-thread-2 全部处于 WAITING ，没有新的线程执行任务所以会造成应用假死状态
 *
 *  增大线程数是一种处理方式，但是生成环境不能确定线程数量是多大，这个也解决不了真正的问题，要解决该类问题就需要进行线程池隔离。
 * @create 2020-11-13
 */
public class ThreadPoolDealThread {

    /**
     * 创建一个只有两个线程的线程池
     */
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    /**
     * 模拟线程池中线程发生依赖关系而参数死锁
     */
    private void process() throws InterruptedException {
        // 第一个阶段的条件控制器
        final CountDownLatch cd1 = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            executorService.execute(() -> {
                ThreadPoolDealThread.print(String.format("L1 %d", finalI));

                // 第二个阶段的条件控制器
                final CountDownLatch cd2 = new CountDownLatch(2);
                for (int k = 0; k < 2; k++) {
                    executorService.execute(() -> {
                        ThreadPoolDealThread.print(String.format("L2 %d", finalI));

                        cd2.countDown();

                    });
                }

                //cd1.countDown();

                try {
                    cd2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                cd1.countDown();
            });
        }

        // 控制条件等待
        cd1.await();

        System.out.println("end");
    }

    /**
     * 信息打印
     *
     * @param message 信息
     */
    private static void print(String message) {
        System.out.printf("[%s] - %s\n", Thread.currentThread().getName(), message);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolDealThread threadPoolDealThread = new ThreadPoolDealThread();
        threadPoolDealThread.process();
    }

}
