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
package org.fufeng.os.concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: jguid
 * @description: 经典问题生产者消费者
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-23
 */
public class ProducerConsumerProblemInfo {

    public static void main(String[] args) throws InterruptedException {
        // 定义一个线程池
        final ExecutorService executorService
                = Executors.newFixedThreadPool(2);
        Container container = new Container();
        runThread(container,executorService);
        // 等待2秒钟后退出进程
        TimeUnit.SECONDS.sleep(5);

        // 关闭线程池
        executorService.shutdownNow();
    }

    /**
     *  执行线程
     * @param container 生产者消费者
     * @param executorService 线程池
     */
    private static void runThread(Container container,ExecutorService executorService ){
        executorService.execute(()-> {
            try {
                container.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()->{
            try {
                container.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        /*new Thread(() -> {
            try {
                container.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                container.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }

    private static class Container{

        // 定义一个消费队列
        private List<Integer> queue = new LinkedList<>();
        // 定义这个队列的大小(默认大小是5)
        private int maxSize=5;
        // 定义一个随机函数
        private Random random = new Random();

        public Container() {
        }

        public Container(int maxSize) {
            this.maxSize = maxSize;
        }

        // 生产者方法实现
        public synchronized void produce() throws InterruptedException {
            while (true){
                // 当前数据如果已经大于上限，那么就主动停止下来
                if (queue.size() >= maxSize){
                    // 唤醒消费线程
                    notify();
                    wait();
                }
                // 开始产生数据
                final int value = random.nextInt(500);
                System.out.printf("produce -> [%d]\n",value);
                // 将数据添加到队列中
                queue.add(value);

                // 休眠500毫秒
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }

        // 消费者方法实现
        public synchronized void consume() throws InterruptedException {
            while (true){
                // 当前队列已经不存在可以消费的数据，那么主动停下来
                if (queue.size() == 0){
                    // 唤醒生产线程
                    notify();
                    wait();
                }
                // 开始消费数据
                final Integer value = queue.remove(0);
                System.out.printf("consume -> [%d]\n",value);

                // 自己休眠500毫秒
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }

    }

}
