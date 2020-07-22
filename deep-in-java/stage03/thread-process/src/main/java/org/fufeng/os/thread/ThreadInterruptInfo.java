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
package org.fufeng.os.thread;

/**
 * @program: jguid
 * @description: {@link }
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-22
 */
public class ThreadInterruptInfo {

    public static void main(String[] args) throws InterruptedException {
        // 定义一个线程，传入一个方法句柄
        Thread thread = new Thread(ThreadInterruptInfo::say);
        // 在线程启动钱执行中断指令，没有效果，因为Thread 里面的Interruptible blocker;没有被初始化
        //thread.interrupt();
        // 启动线程指令
        thread.start();

        // 在线程启动后执行中断指令,生效,interrupt()方法不能直接中断线程，但是可以改变线程中断状态
        thread.interrupt();

        //等待线程完成指令
        thread.join();
    }

    public static void say(){
        if (Thread.currentThread().isInterrupted()){
            System.out.printf("Thread [id : %d] 被中断\n",Thread.currentThread().getId());
            return;
        }
        Object monitor = ThreadInterruptInfo.class;
        synchronized (monitor){
            try {
                monitor.wait(1000);
            } catch (InterruptedException e) {
                // 当前interrupted 状态被清除 == false
                System.out.printf("线程 [id : %d],中断状态是:%s\n"
                        ,Thread.currentThread().getId()
                        ,Thread.currentThread().isInterrupted());
                return;
            }
        }

        System.out.printf("线程 [id : %d],say magic!\n",Thread.currentThread().getId());
    }
}
