/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-10
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.pool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 线程池 第一版
 * 常规使用线程的池化处理
 * @create 2020-11-10
 */
public class ThreadPoolV1 {

    /**
     * 自定义线程池
     */
    private final List<Thread> threadPool = new ArrayList<>();

    /**
     * 从线程池中获取一个线程
     *
     * @param runnable 执行的具体逻辑
     * @return 线程
     */
    public Thread acquire(Runnable runnable) {
        //return new Thread(runnable);
        if (threadPool.size() == 0) {
            Thread thread = new Thread(runnable);
            threadPool.add(thread);
        }
        return threadPool.get(0);
    }

    /**
     * 释放已经使用过的线程信息
     *
     * @param thread 线程
     */
    public void release(Thread thread) {
        threadPool.add(thread);
    }

    public static void main(String[] args) {
        ThreadPoolV1 threadPoolV1 = new ThreadPoolV1();
        final Thread thread = threadPoolV1.acquire(() -> {
            System.out.println("hello , fufeng!");
        });
        threadPoolV1.release(thread);
    }

}
