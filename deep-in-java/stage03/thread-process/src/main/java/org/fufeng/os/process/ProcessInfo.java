/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-22
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.process;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;

/**
 * @program: jguid
 * @description: 进程信息展示
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-22
 */
public class ProcessInfo {

    public static void main(String[] args) {
        // 运行时管理扩展Bean
        final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        // 线程管理扩展Bean
        final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 操作系统管理扩展Bean
        final OperatingSystemMXBean operatingSystemMXBean =
                ManagementFactory.getOperatingSystemMXBean();
        // 通过jdk9的方式获取PID
        long pid = ProcessHandle.current().pid();
        System.out.printf("JDK9 开始的获取进程PID->%d\n",pid);

        final Instant instant = Instant.ofEpochMilli(runtimeMXBean.getStartTime());
        final LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(pid+"->进程启动时间:"+localDate);
        System.out.println(pid+"->进程上线时间:"+runtimeMXBean.getUptime());
        System.out.println(pid+"->进程的线程数:"+threadMXBean.getThreadCount());

        ManagementFactory.getMemoryManagerMXBeans()
                .forEach(memoryManagerMXBean ->
                        System.out.println(
                                Arrays.toString(memoryManagerMXBean.getMemoryPoolNames())));

        System.exit(7);
    }

}
