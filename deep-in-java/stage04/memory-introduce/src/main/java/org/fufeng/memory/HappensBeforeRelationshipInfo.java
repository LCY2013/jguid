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
package org.fufeng.memory;

/**
 * @program: jguid
 * @description: happen-before 原则
 * 读写屏障
 * CPU 单指令原语
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-27
 */
public class HappensBeforeRelationshipInfo {

    /*
        性能开销: Lock > CAS > volatile
        术语: Mutex > CAS > Memory barrier
        底层分析: 多条指令 > CPU 原子指令集 > 单指令或者指令集顺序
     */
    public static void main(String[] args) {

    }

    /**
     * 在下面的这个函数中, 同一线程的情况下
     * 应用程序的排序一定是 action1 发生在 action2 之前
     */
    private static void isSameThread() {
        // action1
        // action2
    }

    /**
     * 一个对象的生命周期中, 它的构造函数(constructor)一定发送在
     * 销毁方法之前(finalizer)
     */
    private static void constructorHappensBeforeFinalizer() {
        //  构造早于销毁
        //  构造函数是在用户线程(main,Thread)中执行
        //  销毁函数(finalizer)是在JVM GC线程中执行
        //  对象的内容存储在heap中，heap对于所有线程来说是共享的(也就是线程不安全的)
        //  假如一个Object对象创建后，没有其他地方引用，被Finalizer线程找到就会被回收掉
    }

    /**
     *  这里这个是一个普通对象，如果作为一个Synchronized 的lock的参数，那么它就相当于一个monitor
     */
    private static final Object monitor = new Object();

    /**
     * The wait methods of class Object (§17.2.1) have lock and unlock actions
     * associated with them; their happens-before relationships are defined by these
     * associated actions.
     *
     * @throws InterruptedException 中断异常
     */
    private static void synchronizedAndWait() throws InterruptedException {
        // JMM 描述：
        // monitor (lock) happens-before monitor.wait()
        // monitor.wait() happens-before monitor (unlock)

        // 实际情况：
        // monitor (lock) synchronizes-with monitor.wait()
        // monitor.wait() synchronizes-with monitor (unlock)

        // if x synchronizes-with y , then x happens-before y

        // 锁monitor对象
        synchronized (monitor){
            // 当 wait() 方法所属对象没有被 synchronized 关键字修饰，
            // 将抛出 IllegalMonitorStateException
            monitor.wait();
        }
    }

    private static void threadStartAndJoin() throws InterruptedException {
        Thread thread = new Thread(()->{
            // action
        });

        // start() 方法 happen-before action 之前
        thread.start();

        // main 线程调用线程 t 的 join() 方法
        // 在 join() 方法返回之前，action动作必须是完成的
        thread.join();
    }
}
