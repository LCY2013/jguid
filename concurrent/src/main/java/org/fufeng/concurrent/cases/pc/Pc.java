/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-16
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.pc;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 生产者-消费者 模式 用于解耦、平衡两种之间的速度差异
 * @create 2020-11-16
 */
public class Pc {

    /**
     * 队列用于处理生产者和消费者之间的通道
     */
    private BlockingQueue<PcTask> queue =
            new ArrayBlockingQueue<>(1000);

    /**
     * 创建协程执行器
     */
    private ExecutorService executor = Executors.newVirtualThreadExecutor();
    //private ExecutorService executor = Executors.newFixedThreadPool(5);

    /**
     * 启动消费者任务队列
     */
    private void start() {

        // 启动5个消费者线程
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                // 处理任务队列
                while (true) {
                    // 拉取所有的任务
                    final Collection<PcTask> pcTasks = poolTasks();

                    // 批处理该批次的任务
                    execBatchTask(pcTasks);
                }
            });
        }
    }

    /**
     * 拉取所有的任务
     */
    private Collection<PcTask> poolTasks() {
        final List<PcTask> tasks = new ArrayList<>();
        // 首次获取通过阻塞方式，避免cpu过度使用
        PcTask pcTask = null;
        try {
            pcTask = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // pcTask 不为null
        while (pcTask != null) {
            tasks.add(pcTask);
            // 费阻塞的方式获取数据
            pcTask = queue.poll();
        }
        // 返回所有的任务
        return tasks;
    }

    /**
     *  批量处理任务
     * @param pcTasks 批量任务
     */
    private void execBatchTask(Collection<PcTask> pcTasks) {

    }

}
