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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 令牌桶算法实现一个简单的限流器
 * <p>
 * 一个是令牌桶算法（Token Bucket），另一个是漏桶算法（Leaky Bucket）。
 * @create 2020-11-16
 */
public class SimpleLimiterV2 {

    /**
     * 当前令牌桶的令牌个数
     */
    private long storedPermits = 0;
    /**
     * 令牌桶令牌的最大个数
     */
    private long maxPermits = 3;
    /**
     * 下一次产生令牌的时间
     */
    private long next = System.nanoTime();

    /**
     * 发放令牌间隔：纳秒
     */
    private long interval = 1000_000_000;

    public SimpleLimiterV2() {
    }

    /**
     * 1/seed 秒产生一个令牌
     *
     * @param seed 步长
     */
    public SimpleLimiterV2(long seed) {
        interval /= seed;
    }

    /**
     * 请求时间在下一令牌产生时间之后, 则
     * 1. 重新计算令牌桶中的令牌数
     * 2. 将下一个令牌发放时间重置为当前时间
     *
     * @param now 当前时间
     */
    private void resync(long now) {
        if (now > next) {
            // 新产生的令牌数
            long newPermits = (now - next) / interval;
            // 新增令牌到令牌桶
            storedPermits = Math.min(maxPermits, newPermits + storedPermits);
            // 将下一次令牌产生的时间设置成当前时间
            next = now;
        }
    }

    /**
     * 预占有令牌，返回能够获取令牌的时间
     *
     * @return 返回能够获取令牌的时间
     */
    private synchronized long reserve(long now) {
        // 计算令牌桶令牌的数量
        resync(now);

        // 能够获取令牌的时间
        long at = next;

        // 令牌桶中能提供的令牌
        long fb = Math.min(1L, storedPermits);

        // 令牌需求，首先减掉令牌桶中的令牌
        long nr = 1 - fb;

        // 重新计算下一个令牌产生的时间
        next += nr * interval;

        // 重新计算令牌桶中的令牌
        storedPermits -= fb;

        return at;
    }

    /**
     * 申请令牌
     */
    private void acquire() {
        // 申请令牌时间
        final long now = System.nanoTime();
        // 预占令牌时间
        final long at = reserve(now);
        // 等待时间
        long waitTime = Math.max(at - now, 0L);

        // 计算出下一个令牌的时间点是否大于0
        if (at > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ExecutorService executor = Executors.newFixedThreadPool(5);
        final SimpleLimiterV2 v2 = new SimpleLimiterV2(5);
        // 用于记录上次运行的时间
        AtomicLong prev = new AtomicLong(System.nanoTime());
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 测试
        for (int i = 0; i < 10; i++) {
            // 限流器限流
            v2.acquire();
            // 提交任务异步执行
            executor.execute(() -> {
                long cur = System.nanoTime();
                // 打印时间
                System.out.println((cur - prev.get()) / 1000_000);
                prev.set(cur);
            });
        }
    }
}
