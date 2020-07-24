/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-23
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.concurrency.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: jguid
 * @description: Synchronized 并发原语
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-23
 */
public class SynchronizedInfo {

    public static void main(String[] args) throws InterruptedException {
        // 定义一个线程池信息
        final ExecutorService executorService =
                Executors.newFixedThreadPool(10);
        // 定义需要计算的操作类
        Algorithm algorithm = new Algorithm();

        // 开启并发计算
        //runThread(executorService, algorithm);
        //runThread(executorService, algorithm);
        //runThread(executorService, algorithm);
        executorService.execute(algorithm::addValue);
        executorService.execute(algorithm::addValue);
        executorService.execute(algorithm::addValue);

        // 关闭线程池
        //executorService.shutdown();

        TimeUnit.SECONDS.sleep(5);
        executorService.shutdown();

        // 最后获取计算后的值信息
        System.out.printf("计算后的值是[%d]",algorithm.getValue());
    }

    /**
     * 在线程中执行加法操作
     *
     * @param executorService 线程池
     * @param algorithm       需要操作的类
     */
    private static void runThread(ExecutorService executorService, Algorithm algorithm) {
        executorService.execute(algorithm::addValue);
    }

    // 定义一个算术类
    private static class Algorithm {
        // 定义一个待加变量,volatile 不能保证数据的一致性，jvm中保证的是可见性，
        // 利用的CPU内存屏障，以及happen before原则
        private volatile Integer value=0;

        // 定义加操作
        public /*synchronized*/ void addValue() {
            //synchronized (Algorithm.class) {
                for (int i = 0; i < 50000; i++) {
                    this.value++;
                }
            //}
        }

        // 获取value值
        public int getValue() {
            return value;
        }

        // 设置value值
        public void setValue(int value) {
            this.value = value;
        }
    }

}
