package com.lcydream.project.jguid.mxbean;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 通过MXBean获取所有线程信息
 *  [5] Attach Listener //添加事件
 * [4] Signal Dispatcher // 分发处理给 JVM 信号的线程
 * [3] Finalizer //调用对象 finalize 方法的线程
 * [2] Reference Handler //清除 reference 线程
 * [1] main //main 线程,程序入口
 */
public class GetThread {

    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId()+": "+threadInfo.getThreadName());
        }
        while (true){

        }
    }
}
