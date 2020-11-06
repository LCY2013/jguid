/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-06
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.reconciliation;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 演示多线程同步对账示例
 * @create 2020-11-06
 */
public class ReconciliationV1 {

    /**
     * 定义固定线程池大小为2
     */
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    /**
     * 模拟注入数据源操作工具
     */
    private final DBUtils dbUtils = new DBUtils();

    /**
     * 第一种方式
     * 开始处理对账信息
     */
    public void process() throws InterruptedException {
        while (dbUtils.hasNext()) {
            // 定义一个线程同步器
            final CountDownLatch countDownLatch = new CountDownLatch(2);
            // 定义未对账信息
            AtomicReference<Object> pObj = new AtomicReference<>();
            // 定义派送单信息
            AtomicReference<Object> dObj = new AtomicReference<>();
            executor.execute(() -> {
                dObj.set(dbUtils.getDOrders());
                countDownLatch.countDown();
            });
            executor.execute(() -> {
                pObj.set(dbUtils.getPOrders());
                countDownLatch.countDown();
            });
            // 等待两个并行线程执行完成
            countDownLatch.await();
            // 开始进行对账处理
            final Object diff = dbUtils.diff(pObj.get(), dObj.get());

            // 将存在异常单数据存入数据源
            dbUtils.save(diff);
        }

        // 执行完成关闭线程池
        executor.shutdown();
    }

    /**
     * 第二种方式
     * <p>
     * 利用CompletableFuture
     */
    public void processFuture() throws ExecutionException, InterruptedException {
        // 查询是否存在未对账的订单信息
        while (dbUtils.hasNext()) {
            final CompletableFuture<Object> dOrderFuture = CompletableFuture.supplyAsync(dbUtils::getDOrders);
            final CompletableFuture<Object> pOrderFuture = CompletableFuture.supplyAsync(dbUtils::getPOrders);
            final Object dOrder = dOrderFuture.get();
            final Object pOrder = pOrderFuture.get();

            // 对账
            final Object diff = dbUtils.diff(pOrder, dOrder);

            // 将存在异常订单数据存入数据源
            dbUtils.save(diff);
        }

    }



    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ReconciliationV1 reconciliation = new ReconciliationV1();
        // 第一种方式
        //reconciliation.process();

        // 第二种方式
        reconciliation.processFuture();
    }
}
