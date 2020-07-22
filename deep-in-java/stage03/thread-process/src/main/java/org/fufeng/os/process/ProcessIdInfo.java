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
import java.lang.management.RuntimeMXBean;

/**
 * @program: jguid
 * @description: 获取进程ID展示
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-22
 */
public class ProcessIdInfo {

    public static void main(String[] args) {
        // jdk9 以前
        getProcessIdBeforeJdk9();
        // jdk9
        getProcessIdInJdk9();
        // jdk10
        getProcessIDInJdk10();
    }

    /**
     *  JDK10 获取进程ID
     */
    private static void getProcessIDInJdk10() {
        final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.printf("[jdk10 获取PID方式],PID=%s\n",runtimeMXBean.getPid());
    }

    /**
     *  JDK9 获取进程ID
     */
    private static void getProcessIdInJdk9() {
        final long pid = ProcessHandle.current().pid();
        System.out.printf("[jdk9 获取PID方式],PID=%s\n",pid);
    }

    /**
     *  JDK9 以前获取进程ID的使用
     */
    private static void getProcessIdBeforeJdk9() {
        // 获取运行时的扩展管理Bean信息
        final RuntimeMXBean runtimeMXBean =
                ManagementFactory.getRuntimeMXBean();
        final String runtimeMXBeanName = runtimeMXBean.getName();
        final String PID =
                runtimeMXBeanName.substring(0, runtimeMXBeanName.indexOf("@"));
        System.out.printf("[jdk9 以前获取PID方式],PID=%s\n",PID);
    }

}
