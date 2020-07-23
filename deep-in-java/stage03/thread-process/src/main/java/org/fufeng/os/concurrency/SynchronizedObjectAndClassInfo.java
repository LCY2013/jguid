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

import java.util.concurrent.TimeUnit;

/**
 * @program: jguid
 * @description: 锁对象和字节码的区别
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-23
 */
public class SynchronizedObjectAndClassInfo {

    /*
        对象存储在堆里面,是否是同一个对象

        字节码对象存储在方法区中
         > 1.8 metadata 物理内存
         < 1.8 Perm 永久代 1.7 是用堆内存实现，其他用的物理内存
     */
    public static void main(String[] args) {
        // 测试锁字节码
        /*new Thread(()-> {
            try {
                SynchronizedObjectAndClassInfo.echo("static");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()-> {
            try {
                SynchronizedObjectAndClassInfo.echo("static");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/

        // 测试锁对象
        new Thread(()-> {
            try {
                SynchronizedObjectAndClassInfo.print("static");
                //SynchronizedObjectAndClassInfo.print(new String("static"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            try {
                SynchronizedObjectAndClassInfo.print("static");
                //SynchronizedObjectAndClassInfo.print(new String("static"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Integer one = 10;
        Integer two = 10;
        Integer three = 1000;
        Integer four = 1000;
        System.out.println(one == two);
        System.out.println(three == four);
    }

    /**
     *  测试锁字节码
     * @param message 需要打印的信息
     */
    public static void echo(String message) throws InterruptedException {
        // 锁Object的字节码信息
        synchronized (Object.class){
            System.out.printf("echo [%s] -> [%s]\n",
                    Thread.currentThread().getName(),message);
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.printf("echo [%s] -> [%s]\n",
                    Thread.currentThread().getName(),message);
        }
    }

    /**
     *  测试锁对象
     * @param message 需要打印的信息
     */
    public static void print(String message) throws InterruptedException {
        synchronized (message){
            System.out.printf("print [%s] -> [%s]\n",
                    Thread.currentThread().getName(),message);
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.printf("print [%s] -> [%s]\n",
                    Thread.currentThread().getName(),message);
        }
    }

}
