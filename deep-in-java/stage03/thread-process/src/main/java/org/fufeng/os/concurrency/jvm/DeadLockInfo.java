/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-23
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.concurrency.jvm;

import java.util.concurrent.TimeUnit;

/**
 * @program: jguid
 * @description: {@link Thread} 死锁示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-23
 */
public class DeadLockInfo {

    public static void main(String[] args) {
        // 设置竞态资源
        final Object lockA = new Object();
        final Object lockB = new Object();

        // 开启线程竟态
        runThread(lockB, lockA);
        runThread(lockA, lockB);
    }

    /**
     *  执行自定义线程
     * @param lockA 第一把锁
     * @param lockB 第二把锁
     */
    private static void runThread(Object lockA, Object lockB) {
        new Thread(()->{
            synchronized (lockA){
                System.out.printf("线程[%s],拿到了[%s]资源!\n",
                        Thread.currentThread().getName(),
                        lockA);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB){
                    System.out.printf("线程[%s],执行完成!\n",
                            Thread.currentThread().getName());
                }
            }
        }).start();
    }

}
