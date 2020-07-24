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

import java.util.concurrent.*;

/**
 * @program: jguid
 * @description: {@link CyclicBarrier} 等待到齐
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-24
 * @see CyclicBarrier
 */
public class CyclicBarrierInfo {

    public static void main(String[] args) throws InterruptedException {
        // 定义一个CyclicBarrier
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

        // 定义一个线程池
        final ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                try {
                    // 线程等待条件满足执行 -> 5 -> 4
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                /*try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                // 打印线程日志
                CyclicBarrierInfo.print();
                // 判断CyclicBarrier是否存在等待数据
                if (cyclicBarrier.getNumberWaiting() == 0){
                    /*try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    // 0 -> 5
                    cyclicBarrier.reset();
                }
            });
            // 主线程休眠等待其他线程执行
            //TimeUnit.SECONDS.sleep(1);
            /*if ((i+1) % 5 == 0){
                cyclicBarrier.reset();
            }*/
            if (cyclicBarrier.getNumberWaiting() == 0){
                TimeUnit.SECONDS.sleep(3);
            }
        }

        // 关掉线程池
        executorService.shutdown();
    }

    /**
     * 根据线程信息打印记录
     */
    private static void print() {
        System.out.printf("Thread-[%s],running...,Time[%d]\n",
                Thread.currentThread().getName(),System.currentTimeMillis());

    }

}
