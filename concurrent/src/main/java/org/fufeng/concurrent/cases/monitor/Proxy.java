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
package org.fufeng.concurrent.cases.monitor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 通过JVM线程模拟监控系统
 * 当监控系统开启监控的时候，被监控系统才是回传数据，等监控系统断开监控的时候，被监控系统就停止工作。
 * @create 2020-11-16
 */
public class Proxy {

    /**
     * 自定义中断标示
     * <p>
     * 在自定义的线程中断处理中，最好是自己定义启动、终止流程，依赖线程的中断响应可能在第三方提供的处理中存在问题
     */
    private volatile boolean terminated;

    /**
     * 线程安全的日期格式化工具
     */
    private ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(() ->
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    );

    /**
     * 监控系统时候已经启动
     */
    private boolean started;

    /**
     * 模拟被监控系统业务处理
     */
    private Thread rptThread;

    /**
     * 启动采集功能
     */
    private synchronized void start() {
        // 只启动一个监控信息处理器
        if (started) {
            return;
        }

        rptThread = new Thread(() -> {
            // 被监控系统数据处理
            while (!terminated) {
                // 业务处理,监控日志信息回报
                report();

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    // 重置标志位，因为JVM在中断异常发生时会清空中断标志位
                    Thread.currentThread().interrupt();
                    //break;
                }
            }
            // 停止后重置标志位
            started = false;
        });

        // 启动被监控系统数据处理
        rptThread.start();

        // 启动标志位设置
        started = true;

        // 重置标志位
        terminated = false;
    }

    /**
     * 处理监控系统的回报
     */
    private void report() {
        System.out.printf("%s - report\n", sdf.get().format(new Date()));
    }

    /**
     * 终止采集功能
     */
    private synchronized void stop() {
        // 先查询线程是否已经启动
        if (started) {
            // 设置线程中断标示位
            terminated = true;
            // 通知线程服务中断
            rptThread.interrupt();
            // 设置线程是否启动标示
            started = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Proxy proxy = new Proxy();
        proxy.start();

        // 模拟监控系统休眠3秒钟
        TimeUnit.SECONDS.sleep(3);

        // 通知被监控系统线程关闭
        proxy.stop();
    }

}
