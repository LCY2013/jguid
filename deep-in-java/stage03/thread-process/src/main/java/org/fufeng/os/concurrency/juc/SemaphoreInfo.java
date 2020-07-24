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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @program: jguid
 * @description: {@link Semaphore} 信号量示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-24
 * @see Semaphore
 */
public class SemaphoreInfo {

    public static void main(String[] args) {
        // 新建一个信号量类实例
        Semaphore semaphore = new Semaphore(2);

        // 新建一个线程池
        final ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                try {
                    // 锁住一个资源
                    semaphore.acquire();
                    // 打印当前线程信息
                    echoThreadInfo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放一个资源
                    semaphore.release();
                }
            });
        }

        // 关闭线程池
        executorService.shutdown();
    }

    private static void echoThreadInfo() {
        System.out.printf("Thread[%s],执行时间:[%d]\n",
                Thread.currentThread().getName(), System.currentTimeMillis() % 100000);
        try {
            // 执行线程休眠500毫秒
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
