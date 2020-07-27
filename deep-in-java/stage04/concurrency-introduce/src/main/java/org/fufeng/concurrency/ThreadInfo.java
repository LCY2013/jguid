/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-27
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrency;

/**
 * @program: jguid
 * @description: {@link Thread} 线程使用
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-27
 * @see Thread
 */
public class ThreadInfo {

    public static void main(String[] args) throws InterruptedException {
        // 创建 Java线程
        // Java 普通线程对象 和 JVM OS线程不是同一个对象
        Thread thread = new Thread(ThreadInfo::print);

        // 通过main线程启动普通线程
        thread.start(); // pthread_create()
        // 通过线程join操作等待普通线程执行完成
        thread.join();  // pthread_join()

        // 当线程isAlive 返回false时，JVM线程已经消亡(delete this)
        System.out.printf("Thread-[%d],isAlive-[%s],state-[%s]\n",
                thread.getId(),thread.isAlive(),thread.getState());
    }

    private static void print(){
        System.out.printf("ThreadId-[%d],magic say fufeng!\n",
                Thread.currentThread().getId());
    }
}
