package com.lcydream.project.jguid.mxbean;

/**
 * 通过当前线程获取根线程组，从而获取所有的线程信息
 */
public class GetThreadGroup{

    public static void main(String[] args) {
        ThreadGroup currentGroup =
                Thread.currentThread().getThreadGroup();
        while (currentGroup.getParent() != null){
            currentGroup = currentGroup.getParent();
        }
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        currentGroup.enumerate(lstThreads);
        for (int i = 0; i < noThreads; i++)
            System.out.println("线程号：" + i + " = " + lstThreads[i].getName());
    }
}
