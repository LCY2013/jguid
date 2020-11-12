/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-12
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 每次为线程分配一个唯一的ID
 * @create 2020-11-12
 */
public class ThreadId {

    /**
     * id 生成器
     */
    private final static AtomicInteger idGeneric =
            new AtomicInteger(0);

    /**
     * 线程ID 生成不同的 id
     */
    private final ThreadLocal<Integer> threadID =
            ThreadLocal.withInitial(idGeneric::getAndIncrement);

    /**
     * 线程获取自己的id
     *
     * @return 线程自己的id
     */
    private Integer getThreadId() {
        return threadID.get();
    }

    public static void main(String[] args) {
        // 模拟多线程去生成自己的id，看是否会重复
        final ExecutorService executorService =
                Executors.newFixedThreadPool(4);

        final ThreadId threadId = new ThreadId();
        for (int k = 0; k < 4; k++) {
            executorService.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    System.out.printf("[%s]-%d\n",
                            Thread.currentThread().getName(),
                            threadId.getThreadId());
                }
            });
        }

        executorService.shutdown();
    }
}
