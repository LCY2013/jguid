/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-10
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.pool.v6;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description {@link CompletableFuture} 异步化使用
 * @create 2020-11-10
 * @see CompletableFuture
 */
public class ThreadPoolV6 {

    public static void main(String[] args) {
        // 烧水流程
        final CompletableFuture<Void> waterFuture = CompletableFuture.runAsync(() -> {
            System.out.println("waterFuture - 洗水壶...");

            sleep(500,TimeUnit.MICROSECONDS);

            System.out.println("WaterFuture - 烧开水...");

            sleep(500,TimeUnit.MICROSECONDS);
        });

        // 那茶叶流程
        final CompletableFuture<String> teaFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("TeaFuture - 洗茶壶...");

            sleep(500,TimeUnit.MICROSECONDS);

            System.out.println("TeaFuture - 洗茶具...");

            sleep(500,TimeUnit.MICROSECONDS);

            System.out.println("TeaFuture - 拿到茶叶...");

            sleep(500,TimeUnit.MICROSECONDS);

            return "大红袍";
        });

        // 煮茶流程
        final CompletableFuture<String> teaOk = waterFuture.thenCombine(teaFuture, (__, tf) -> {
            System.out.println("拿到茶叶 - "+tf);

            sleep(500,TimeUnit.MICROSECONDS);

            System.out.println("煮茶...");
            return String.format("上茶 - %s",tf);
        });

        // 等待结果
        System.out.println((teaOk.join()));
        //teaOk.get()
    }

    private static void sleep(long time, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
