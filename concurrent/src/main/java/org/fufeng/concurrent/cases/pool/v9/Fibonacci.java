/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-11
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.pool.v9;

import java.util.concurrent.RecursiveTask;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description Fork/join 计算Fibonacci
 *
 *  F(0)=0，F(1)=1, F(n)=F(n - 1)+F(n - 2)
 *
 * @create 2020-11-11
 */
public class Fibonacci extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1253855767243549045L;

    /**
     * 计算 的数据
     */
    private int num;

    public Fibonacci(int num) {
        this.num = num;
    }

    /**
     *  递归任务
     *
     *    Fibonacci(n - 1) 使用异步子任务，这是通过 f1.fork()这条语句实现
     *
     * @return Fibonacci 值
     */
    @Override
    protected Integer compute() {
        // 终止边界
        if (this.num <= 1) {
            return this.num;
        }

        final Fibonacci fibonacci1 = new Fibonacci(this.num  - 1);
        // 创建异步子任务
        fibonacci1.fork();

        // 创建异步子任务
        final Fibonacci fibonacci2 = new Fibonacci(this.num - 2);
        fibonacci2.fork();

        // 等待子结果合并，合并结果
        return fibonacci2.join() + fibonacci1.join();
    }
}
