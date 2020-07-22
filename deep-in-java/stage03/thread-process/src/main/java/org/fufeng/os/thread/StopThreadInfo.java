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

import java.util.concurrent.Executors;

/**
 * @program: jguid
 * @description: 停止线程使用
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-22
 * @see Thread
 */
public class StopThreadInfo {

    public static void main(String[] args) {
        StoppableAction action = new StoppableAction();
        Thread thread = new Thread(action);

        // 启动线程指令(并不一定执行)
        thread.start();

        // 通过调整状态终止该线程执行,设置stopped状态为true
        action.stopped();

        // 如果存在线程池，那么上面的操作可能存在问题
        Executors.newFixedThreadPool(3);  // 这里的线程数是固定的
        Executors.newCachedThreadPool(); // 这里的线程是等价于无限多的
    }

    // 定义一个线程执行器
    private static class StoppableAction implements Runnable{

        /**
         *  定义一个线程停止的标示，在这里通过volatile修饰等价于AtomicBoolean
         */
        private volatile boolean stopped;

        @Override
        public void run() {
            if (stopped){
                // 通过这种方式可以终止本次执行，或者通过抛出异常也可以kill到这个线程
                System.out.println("action stopped...");
                return;
            }
            System.out.println("action running...");
        }

        public void stopped() {
            this.stopped = true;
        }
    }

}
