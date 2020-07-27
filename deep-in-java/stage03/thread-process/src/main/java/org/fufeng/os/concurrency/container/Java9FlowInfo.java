/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-27
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.concurrency.container;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: {@link Stream} 流式操作
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-27
 * @see Stream 流抽象
 */
public class Java9FlowInfo {

    public static void main(String[] args) {
        // java7 try-with-resource
        try (SubmissionPublisher<String> publisher = new SubmissionPublisher<>()){
            // 订阅
            publisher.subscribe(new Flow.Subscriber<String>() {
                // Message control linking a {@link Publisher} and {@link Subscriber}.
                private Flow.Subscription subscription;
                @Override
                public void onSubscribe(Flow.Subscription subscription) {
                    this.subscription = subscription;
                    // 打印出订阅连接器
                    printf("已订阅");
                    // 设置订阅量的大小(Long.MAX_VALUE)
                    subscription.request(Long.MAX_VALUE);
                }

                @Override
                public void onNext(String item) {
                    // 查看订阅的消息是否是退出指令
                    if ("exit".equals(item)){
                        // 取消订阅信息
                        this.subscription.cancel();
                        return;
                    }else if ("exception".equalsIgnoreCase(item)){
                        throw new RuntimeException("throw a exception");
                    }
                    printf("获取到数据->"+item);
                }

                @Override
                public void onError(Throwable throwable) {
                    printf("onError ->"+throwable);
                }

                @Override
                public void onComplete() {
                    printf("操作完成");
                }
            });

            // 发布者发布数据
            publisher.submit("fufeng");
            publisher.submit("magic");

            // 抛出异常
            publisher.submit("exception");

            // 发送退出指令
            publisher.submit("exit");

            // 继续发送数据,看订阅者状态,这里的数据就会被忽略
            publisher.submit("l");
            publisher.submit("c");
            publisher.submit("y");

            // 订阅
            /*publisher.subscribe(new Flow.Subscriber<String>() {
                // Message control linking a {@link Publisher} and {@link Subscriber}.
                private Flow.Subscription subscription;
                @Override
                public void onSubscribe(Flow.Subscription subscription) {
                    this.subscription = subscription;
                    // 打印出订阅连接器
                    printf("已订阅");
                    // 设置订阅量的大小(Long.MAX_VALUE)
                    subscription.request(Long.MAX_VALUE);
                }

                @Override
                public void onNext(String item) {
                    // 查看订阅的消息是否是退出指令
                    if ("exit".equals(item)){
                        // 取消订阅信息
                        this.subscription.cancel();
                        return;
                    }else if ("exception".equalsIgnoreCase(item)){
                        throw new RuntimeException("throw a exception");
                    }
                    printf("获取到数据->"+item);
                }

                @Override
                public void onError(Throwable throwable) {
                    printf("onError ->"+throwable);
                }

                @Override
                public void onComplete() {
                    printf("操作完成");
                }
            });*/

            // 获取到执行器
            ExecutorService executorService =
                    (ExecutorService)publisher.getExecutor();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *  打印方法抽象
     * @param object 打印对象
     */
    private static void printf(Object object){
        System.out.printf("Thread-[%s],(%s)\n",
                Thread.currentThread().getName(),object);
    }
}
