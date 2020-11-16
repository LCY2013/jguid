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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 日志组件异步刷盘逻辑
 * <p>
 * 利用生产者 - 消费者模式可以轻松地支持一种分阶段提交的应用场景。
 * <p>
 * 写文件如果同步刷盘性能会很慢，所以对于不是很重要的数据，往往采用异步刷盘的方式。
 * 采用的就是异步刷盘方式，刷盘的时机是：
 * 1. ERROR 级别的日志需要立即刷盘；
 * 2. 数据积累到 500 条需要立即刷盘；
 * 3. 存在未刷盘数据，且 5 秒钟内未曾刷盘，需要立即刷盘。
 * @create 2020-11-16
 */
public class LogComponentV2 {

    /**
     * 用于日志消费线程终止的毒丸对象
     */
    private final LogMsg poisonPill = new LogMsg(LogMsg.LEVEL.ERROR, "");

    /**
     * 是否立即停止
     */
    private volatile boolean shutdownNow;

    /**
     * 日志处理队列
     */
    private BlockingQueue<LogMsg> queue =
            new ArrayBlockingQueue<>(2000);

    /**
     * 日志批处理数据量设置
     */
    private final int batchSize = 500;

    /**
     * 创建虚拟线程池去处理任务
     */
    private final ExecutorService executor = Executors.newFixedThreadPool(1);

    /**
     * 打印info相关的日志信息
     *
     * @param logMsg 日志文本
     */
    public void INFO(String logMsg) {
        try {
            queue.put(new LogMsg(LogMsg.LEVEL.INFO, logMsg));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印error相关的日志信息
     *
     * @param logMsg 日志文本
     */
    public void ERROR(String logMsg) {
        try {
            queue.put(new LogMsg(LogMsg.LEVEL.INFO, logMsg));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动日志组件，进行异步刷盘等操作
     */
    public void start() {
        // 创建一个本地文件
        final File file = new File("catalina", ".log");
        // 定义文件写
        final FileWriter fw;
        try {
            fw = new FileWriter(file);
            executor.execute(() -> {
                try {
                    // 未刷盘数量
                    int curInx = 0;
                    // 当前时间
                    long preTime = System.currentTimeMillis();

                    while (true) {
                        final LogMsg logMsg = queue.poll(5, TimeUnit.SECONDS);
                        // 开始写日志
                        if (Objects.nonNull(logMsg)) {
                            fw.write(logMsg.toString());
                            ++curInx;
                        }

                        // 如果刷盘数没有达到阀值，就进行下一波处理
                        if (curInx <= 0) {
                            continue;
                        }

                        // 1. ERROR 级别的日志需要立即刷盘；
                        // 2. 数据积累到 500 条需要立即刷盘；
                        // 3. 存在未刷盘数据，且 5 秒钟内未曾刷盘，需要立即刷盘。
                        if (Objects.nonNull(logMsg) && (logMsg.getLevel() == LogMsg.LEVEL.ERROR
                                || curInx == batchSize || System.currentTimeMillis() - preTime > 5000)) {
                            // 文件写操作刷新到磁盘
                            fw.flush();
                            // 当前写日志的下标重置为0
                            curInx = 0;
                            // 上一次时间戳重置为当前时间戳
                            preTime = System.currentTimeMillis();
                        }

                        // 需要终止条件的对象，直接跳出循环
                        if (poisonPill.equals(logMsg)) {
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    // 重置线程中断标志位
                    Thread.currentThread().interrupt();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fw.flush();
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭日志组件
     */
    public void stop() {
        if (!executor.isTerminated()) {
            executor.shutdown();
            try {
                queue.put(poisonPill);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 立即停止日志组件
     */
    public void stopNow() {
        if (!executor.isTerminated()) {
            executor.shutdown();
            try {
                queue.put(poisonPill);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
