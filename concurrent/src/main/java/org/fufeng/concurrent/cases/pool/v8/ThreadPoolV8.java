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
package org.fufeng.concurrent.cases.pool.v8;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description ExecutorService + Future 实现三个异步同时查询操作
 * @create 2020-11-11
 * @see Future
 * @see ExecutorService
 */
public class ThreadPoolV8 {

    public static void main(String[] args) {
        // 示例一
        case01();
        // 示例二
        case02();
    }

    /**
     * ExecutorService + Future 实现三个异步同时查询操作
     */
    private static void case01() {
        long startTime = System.currentTimeMillis();
        // 创建一个线程池用于执行异步方法
        final ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 向jd查询
        final Future<Integer> jdFuture = executorService.submit(Query::queryPriceByJD);
        // 向tm查询
        final Future<Integer> tmFuture = executorService.submit(Query::queryPriceByTM);
        // 向pdd查询
        final Future<Integer> pddFuture = executorService.submit(Query::queryPriceByPDD);

        // 保存jd查询结果
        executorService.execute(() -> {
            try {
                Save.save(jdFuture.get(), "JD");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 保存tm查询结果
        executorService.execute(() -> {
            try {
                Save.save(tmFuture.get(), "TM");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 保存pdd查询结果
        executorService.execute(() -> {
            try {
                Save.save(pddFuture.get(), "PDD");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();

        System.out.printf("[%s] spend %d\n","case01",System.currentTimeMillis() - startTime);
    }

    /**
     * ExecutorService + Future + 阻塞队列 实现三个异步同时查询操作
     */
    private static void case02() {
        long startTime = System.currentTimeMillis();
        // 线程池
        final ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 新建阻塞队列
        final BlockingQueue<Map<String, Integer>> queue = new ArrayBlockingQueue<>(3);

        // 向jd查询价格
        executorService.execute(() -> {
            final Map<String, Integer> map = new HashMap<>();
            map.put("JD", Query.queryPriceByJD());
            queue.add(map);
        });

        // 向tm查询价格
        executorService.execute(() -> {
            final Map<String, Integer> map = new HashMap<>();
            map.put("TM", Query.queryPriceByTM());
            queue.add(map);
        });

        // 向pdd查询价格
        executorService.execute(() -> {
            final Map<String, Integer> map = new HashMap<>();
            map.put("PDD", Query.queryPriceByPDD());
            queue.add(map);
        });

        /*for (Map<String, Integer> map : queue) {
            for (Map.Entry<String,Integer> entry : map.entrySet()) {
                Save.save(entry.getValue(),entry.getKey());
            }
        }*/

        // 同步方式
        /*for (int i = 0; i < 3; i++) {
            try {
                final Map<String, Integer> map = queue.take();
                for (Map.Entry<String,Integer> entry : map.entrySet()) {
                    Save.save(entry.getValue(),entry.getKey());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        // 异步方式
        executorService.execute(()->{
            for (int i = 0; i < 3; i++) {
                try {
                    final Map<String, Integer> map = queue.take();
                    for (Map.Entry<String,Integer> entry : map.entrySet()) {
                        Save.save(entry.getValue(),entry.getKey());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();

        System.out.printf("[%s] spend %d\n","case02",System.currentTimeMillis() - startTime);
    }

}
