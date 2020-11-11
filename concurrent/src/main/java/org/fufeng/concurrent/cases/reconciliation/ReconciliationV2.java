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

import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 演示多线程同步对账示例
 * @create 2020-11-06
 */
public class ReconciliationV2 {

    /**
     * 订单队列
     */
    private final Vector<Object> pOrder = new Vector<>();

    /**
     * 派送单队列
     */
    private final Vector<Object> dOrder = new Vector<>();

    /**
     * 定义固定线程池大小为2
     */
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    /**
     * 定义同步线程池
     */
    private final ExecutorService checkExecutor = Executors.newFixedThreadPool(1);

    /**
     * 模拟注入数据源操作工具
     */
    private final DBUtils dbUtils = new DBUtils();

    /**
     * 同步器
     * <p>
     * 完成两个订单查询后执行后面的回调
     */
    private final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> checkExecutor.execute(this::check));
    //private final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, this::check);

    /**
     * 开始对账
     */
    private void check() {
        // 开始进行对账处理
        final Object diff = dbUtils.diff(pOrder.remove(0), dOrder.remove(0));
        // 将存在异常单数据存入数据源
        dbUtils.save(diff);

        if (pOrder.size() == 0 && dOrder.size() == 0) {
            // 关闭线程池
            checkExecutor.shutdown();
        }
    }

    /**
     * 第一种方式
     * 开始处理对账信息
     */
    public void process() {
        while (dbUtils.hasNext()) {
            executor.execute(() -> {
                // 派发单查询
                dOrder.add(dbUtils.getDOrders());
                try {
                    // 执行等待
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            executor.execute(() -> {
                // 订单查询
                pOrder.add(dbUtils.getPOrders());
                try {
                    // 执行等待
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        // 执行完成关闭线程池
        executor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        final ReconciliationV2 reconciliation = new ReconciliationV2();
        reconciliation.process();
    }
}
