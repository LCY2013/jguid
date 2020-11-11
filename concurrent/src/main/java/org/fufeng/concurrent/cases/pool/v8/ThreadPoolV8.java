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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description ExecutorService + Future 实现三个异步同时查询操作
 * CompletionService 实现询价系统
 * <p>
 * CompletionService 实现 Dubbo 中的 Forking Cluster
 * @create 2020-11-11
 * @see Future
 * @see ExecutorService
 */
public class ThreadPoolV8 {

    public static void main(String[] args) {
        // 示例一
        // case01();
        // 示例二
        // case02();
        // 示例三
        // case03();
        // 示例四
        case04();
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

        System.out.printf("[%s] spend %d\n", "case01", System.currentTimeMillis() - startTime);
    }

    /**
     * ExecutorService + Future + 阻塞队列 实现三个异步同时查询操作
     * <p>
     * CompletionService 原理展示
     *
     * @see CompletionService
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
        executorService.execute(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    // take 拿不到数据会阻塞
                    // poll 拿不到数据就返回null，如何加上超时时间，就会在超时时间上拿到不到数据在返回null
                    final Map<String, Integer> map = queue.take();
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        Save.save(entry.getValue(), entry.getKey());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();

        System.out.printf("[%s] spend %d\n", "case02", System.currentTimeMillis() - startTime);
    }

    /**
     * CompletionService 实现询价系统
     */
    private static void case03() {
        long startTime = System.currentTimeMillis();
        // 线程池
        final ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 定义CompletionService
        final CompletionService<Map<String, Integer>> completionService = new ExecutorCompletionService<>(executorService);
        // 向jd询问价格
        completionService.submit(() -> {
            final Map<String, Integer> map = new HashMap<>();
            map.put("JD", Query.queryPriceByJD());
            return map;
        });
        // 向tm询问价格
        completionService.submit(() -> {
            final Map<String, Integer> map = new HashMap<>();
            map.put("TM", Query.queryPriceByTM());
            return map;
        });
        // 向pdd询问价格
        completionService.submit(() -> {
            final Map<String, Integer> map = new HashMap<>();
            map.put("PDD", Query.queryPriceByPDD());
            return map;
        });

        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    // 获取完成的事件
                    final Map<String, Integer> map = completionService.take().get();
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        Save.save(entry.getValue(), entry.getKey());
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();

        System.out.printf("[%s] spend %d\n", "case02", System.currentTimeMillis() - startTime);
    }

    /**
     * CompletionService 实现 Dubbo 中的 Forking Cluster
     * <p>
     * Dubbo 中有一种叫做Forking 的集群模式，这种集群模式下，支持并行地调用多个查询服务，只要有一个成功返回结果，整个服务就可以返回了。
     * <p>
     * 例如你需要提供一个地址转坐标的服务，为了保证该服务的高可用和性能，你可以并行地调用 3 个地图服务商的 API，然后只要有 1 个正确返回了结果 r，那么地址转坐标这个服务就可以直接返回 r 了。
     * <p>
     * 这种集群模式可以容忍 2 个地图服务商服务异常，但缺点是消耗的资源偏多。
     */
    private static void case04() {
        // 定义线程池
        final ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 定义CompletionService
        final CompletionService<Integer> cs = new ExecutorCompletionService<>(executorService);

        // 定义Future集合用于处理后续的取消任务
        final List<Future<Integer>> futures = new ArrayList<>();

        // 通过 gd查询
        futures.add(cs.submit(GeoService::geoCoderByGD));
        // 通过 bd查询
        futures.add(cs.submit(GeoService::geoCoderByBD));
        // 通过 tx查询
        futures.add(cs.submit(GeoService::geoCoderByTX));

        try {
            // 最多三次轮训，因为一次调用查询了三个服务
            for (int i = 0; i < 3; i++) {
                // 只要其中有一个返回就可以获取到数据
                final Integer result = cs.take().get();
                if (result != null) {
                    System.out.printf("result %d\n",result);
                    break;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 取消任务
            for (Future<Integer> future : futures) {
                future.cancel(true);
            }

            executorService.shutdown();
        }
    }

}
