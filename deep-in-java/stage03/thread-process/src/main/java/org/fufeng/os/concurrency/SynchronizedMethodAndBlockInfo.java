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
package org.fufeng.os.concurrency;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @program: jguid
 * @description: Synchronized 修饰方法和代码块区别？
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-23
 */
public class SynchronizedMethodAndBlockInfo {

    public static void main(String[] args) {
        /*
            Synchronized 修饰方法
            1、如果方法是普通方法作用的锁对象是当前的实例类
            2、如果方法是静态方法作用的锁对象就是类的字节码对象
            Synchronized 修饰代码块
            1、作用的是代码块里面的给定对象(类实例，字节码，字符，引用对象)
         */
        // 定义两个不同的对象
        SynchronizedMethodAndBlockInfo infoA = new SynchronizedMethodAndBlockInfo();
        SynchronizedMethodAndBlockInfo infoB = new SynchronizedMethodAndBlockInfo();

        // 定义两个线程执行方法
        /*new Thread(()->{
            try {
                SynchronizedMethodAndBlockInfo.echo("static");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                SynchronizedMethodAndBlockInfo.echo("static");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/

        /*new Thread(()->{
            try {
                infoA.echo(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                infoB.echo(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/

        new Thread(()->{
            try {
                //infoA.print("magic");
                infoA.print(new String("magic"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                //infoB.print("magic");
                infoB.print(new String("magic"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     *  锁静态方法
     * @param message 需要打印的内容
     */
    synchronized static void echo(String message) throws InterruptedException {
        System.out.printf("[%s] -> synchronized static fufeng say : [%s]\n",
                Thread.currentThread().getName(),message);
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.printf("[%s] -> synchronized static fufeng say : [%s]\n",
                Thread.currentThread().getName(),message);
    }

    /**
     *  锁实例方法
     * @param message 需要打印的内容
     */
    private synchronized void echo(Object message) throws InterruptedException {
        System.out.printf("[%s] -> synchronized fufeng say : [%s]\n",
                Thread.currentThread().getName(),message);
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.printf("[%s] -> synchronized fufeng say : [%s]\n",
                Thread.currentThread().getName(),message);
    }

    /**
     *  锁代码块
     *   这里看出来jvm对String类型的优化,""里面的字符串是会使用常量池中的同一个对象,这里锁主的字符串其实是同一个
     *   如果使用new String("") 产生的字符串对象,那么这里锁就不是同一个对象
     * @param message 需要打印的内容
     */
    private void print(String message) throws InterruptedException {
        if (Objects.nonNull(message)) {
            synchronized (message) {
                System.out.printf("[%s] -> synchronized block fufeng say : [%s]\n",
                        Thread.currentThread().getName(),message);
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.printf("[%s] -> synchronized block fufeng say : [%s]\n",
                        Thread.currentThread().getName(),message);
            }
        }
    }
}
