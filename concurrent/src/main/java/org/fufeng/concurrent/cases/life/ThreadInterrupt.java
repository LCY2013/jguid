/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-03
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.life;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 演示 Thread中的 interrupt()方法、isInterrupted()
 * @create 2020-11-03
 */
public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
        final Thread thread1 = new Thread(() -> {
            while (true) {
                System.out.println("thread1 run status "+Thread.currentThread().isInterrupted());
                if (Thread.currentThread().isInterrupted()){
                    break;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 下面注释的内容打开模拟线程二去中断线程一，线程一在休眠的时候被线程二中断
        // 这个时候thread1.isInterrupted() == true 原本线程一就该在这个时候退出
        // 但是sleep()方法被中断，它就会重置interrupt状态 这个时候thread1.isInterrupted() == false，进而造成死循环
        final Thread thread2 = new Thread(()->{
            /*try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            thread1.interrupt();
            System.out.println("thread2 interrupt thread1 status "+thread1.isInterrupted());
        });

        thread1.start();
        thread2.start();

        TimeUnit.SECONDS.sleep(3);
    }

}
