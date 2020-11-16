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

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 令牌桶算法实现一个简单的限流器
 * @create 2020-11-16
 */
public class SimpleLimiterV1 {

    /**
     * 下一次产生令牌的时间
     */
    private long next = System.nanoTime();

    /**
     * 发放令牌间隔：纳秒
     */
    private long interval = 1000_000_000;

    /**
     * 预占有令牌，返回能够获取令牌的时间
     *
     * @return 返回能够获取令牌的时间
     */
    private synchronized long reserve(long now) {
        // 请求时间在下一次令牌产生时间之后
        // 重新计算令牌的下一次产生时间
        if (now > next) {
            // 将下一次令牌生成的时间设置为当前获取令牌的时间
            next = now;
        }

        // 能够获取令牌的时间
        long at = next;
        // 设置下一次获取令牌的时间
        next += interval;

        // 返回线程需要等待的时间
        return Math.max(at, 0L);
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
        long waitTime = Math.max(at, 0L);

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
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
    }

}
