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
 * @description 模拟JVM协程模拟监控系统
 * 当监控系统开启监控的时候，被监控系统才是回传数据，等监控系统断开监控的时候，被监控系统就停止工作。
 * @create 2020-11-16
 */
public class VirtualProxy {

    /**
     *  线程安全的日期格式化工具
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

        rptThread = Thread.startVirtualThread(() -> {
            // 被监控系统数据处理
            while (!Thread.currentThread().isInterrupted()) {
                // 业务处理,监控日志信息回报
                report();

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    // JVM 在异常的时候会重置中断异常标示位，所以这里需要重置标志位
                    Thread.currentThread().interrupt();
                    //break;
                }
            }
            // 停止后重置标志位
            started = false;
        });

        rptThread.setName("virtualProxy");
    }

    /**
     * 处理监控系统的回报
     */
    private void report() {
        System.out.printf("%s - %s - report\n",Thread.currentThread().getName(),sdf.get().format(new Date()));
    }

    /**
     * 终止采集功能
     */
    private synchronized void stop() {
        // 先查询线程是否已经启动
        if (started) {
            rptThread.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final VirtualProxy proxy = new VirtualProxy();
        proxy.start();

        // 模拟监控系统休眠3秒钟
        TimeUnit.SECONDS.sleep(3);

        // 通知被监控系统线程关闭
        proxy.stop();
    }

}
