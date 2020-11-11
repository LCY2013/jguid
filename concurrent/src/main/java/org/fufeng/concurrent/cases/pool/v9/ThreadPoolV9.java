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

import java.util.concurrent.ForkJoinPool;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 对于简单的并行任务，可以通过“线程池 +Future”的方案来解决；
 * 如果任务之间有聚合关系，无论是 AND 聚合还是 OR 聚合，都可以通过 CompletableFuture 来解决；
 * 而批量的并行任务，则可以通过 CompletionService 来解决。
 * <p>
 * 分治思想 Fork/Join  大数据领域的 MapReduce
 * <p>
 * ForkJoinTask 是一个抽象类，它的方法有很多，最核心的是 fork() 方法和 join() 方法，
 * 其中 fork() 方法会异步地执行一个子任务，
 * 而 join() 方法则会阻塞当前线程来等待子任务的执行结果。
 * <p>
 * ForkJoinTask 有两个子类——RecursiveAction 和 RecursiveTask，都是用递归的方式来处理分治任务的。
 * 这两个子类都定义了抽象方法compute()，不过区别是 RecursiveAction 定义的 compute() 没有返回值，
 * 而RecursiveTask 定义的 compute() 方法是有返回值的，这两个子类也是抽象类，在使用的时候，需要你定义子类去扩展
 * @create 2020-11-11
 */
public class ThreadPoolV9 {

    public static void main(String[] args) {
        // 创建分治线程池
        final ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        // 创建分治任务 0 1 1 2 3 5
        final Fibonacci fibonacci = new Fibonacci(6);

        // 执行分治任务
        final Integer result = forkJoinPool.invoke(fibonacci);

        // 输出结果
        System.out.println(result);
    }

}
