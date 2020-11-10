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
package org.fufeng.concurrent.cases.pool.v7;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 演示 {@link CompletableFuture}
 * @create 2020-11-10
 * @see CompletableFuture
 */
public class ThreadPoolV7 {

    public static void main(String[] args) {
        // case01();
        // case02();
        // case03();
        // case04();
        case05();
    }

    /**
     * CompletableFuture 描述串行关系
     */
    public static void case01() {
        final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello") // 异步 (1)
                .thenApply(str -> str + " , fufeng") // 同步 (2)
                .thenApply(String::toUpperCase);// 同步 (3)

        // 流程 -> 先异步 后面同步
        // 执行顺序 1 -> 2 -> 3
        System.out.println(completableFuture.join());
        //System.out.println(completableFuture.get());
    }

    /**
     * CompletableFuture 描述AND关系
     * <p>
     * thenCombine、thenAcceptBoth 和 runAfterBoth
     * <p>
     * fn、consumer、action
     */
    public static void case02() {
        final CompletableFuture<Integer> integerTen = CompletableFuture.supplyAsync(() -> {
            final int num = new Random().nextInt();
            System.out.printf("[%s] [%d] cal\n", Thread.currentThread().getName(), num);
            return num % 10;
        });

        final CompletableFuture<Integer> integerFive = CompletableFuture.supplyAsync(() -> {
            final int num = new Random().nextInt();
            System.out.printf("[%s] [%d] cal\n", Thread.currentThread().getName(), num);
            return num % 5;
        });

        // 直接使用integerFive 线程
        final CompletableFuture<Integer> completableFuture =
                integerTen.thenCombineAsync(integerFive, (ten, five) -> {
                    System.out.printf("[%s] cal\n", Thread.currentThread().getName());
                    return ten * five;
                });

        System.out.println(completableFuture.join());
    }

    /**
     * CompletableFuture 描述OR关系
     * <p>
     * applyToEither、acceptEither 和 runAfterEither
     * <p>
     * fn、consumer、action
     */
    public static void case03() {
        final CompletableFuture<Integer> integerFour = CompletableFuture.supplyAsync(() -> {
            final int num = new Random().nextInt(4);
            System.out.printf("[%s] [%d] cal\n", Thread.currentThread().getName(), num);
            try {
                TimeUnit.SECONDS.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return num;
        });

        final CompletableFuture<Integer> integerFive = CompletableFuture.supplyAsync(() -> {
            final int num = new Random().nextInt(5);
            System.out.printf("[%s] [%d] cal\n", Thread.currentThread().getName(), num);
            try {
                TimeUnit.SECONDS.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return num;
        });

        // 选择最先执行完成的结果集
        final CompletableFuture<Integer> completableFuture =
                integerFour.applyToEither(integerFive, num -> num);

        System.out.println(completableFuture.join());
    }

    /**
     * 异常处理
     */
    public static void case04() {
        final CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> 7 / 0)
                .thenApply(num -> num++)
                .exceptionally(e -> {   // 类似与try{}catch(){} 捕获异常
                    System.out.println(e.getMessage());
                    return 0;
                }).whenComplete((num, e) -> {  // 类似与try{}finally{} 不支持返回值 finally
                    if (e == null) {
                        System.out.println(num);
                    }
                });
        System.out.println(completableFuture.join());
    }

    /**
     * 异常处理
     */
    public static void case05() {
        final CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                .thenApply(num -> num++)
                .handle((num, e) -> {  // 类似与try{}finally{} 不支持返回值 finally
                    if (e == null) {
                        System.out.println(num);
                    }
                    return num*2;
                });
        System.out.println(completableFuture.join());
    }

}
