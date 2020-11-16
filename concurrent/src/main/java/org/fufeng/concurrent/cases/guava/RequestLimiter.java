/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-16
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 请求限流器
 * <p>
 * 利用guava实现请求限流器
 * @create 2020-11-16
 */
public class RequestLimiter {

    /**
     * 限流器流速 ：2个请求/秒
     * <p>
     * 每500毫秒通过一个请求
     */
    private RateLimiter rateLimiter = RateLimiter.create(2.0);

    /**
     * 任务执行线程池
     */
    private ExecutorService executor = Executors.newFixedThreadPool(5);

    private void request() {
        // 用于记录上次运行的时间
        AtomicLong prev = new AtomicLong(System.nanoTime());
        // 测试
        for (int i = 0; i < 10; i++) {
            // 限流器限流
            rateLimiter.acquire();
            // 提交任务异步执行
            executor.execute(() -> {
                long cur = System.nanoTime();
                // 打印时间
                System.out.println((cur - prev.get()) / 1000_000);
                prev.set(cur);
            });
        }
    }

    public static void main(String[] args) {
        RequestLimiter rl = new RequestLimiter();
        rl.request();
    }
}
