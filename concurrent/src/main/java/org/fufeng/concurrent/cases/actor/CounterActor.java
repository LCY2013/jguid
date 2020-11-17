/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-17
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 基于Actor模型的累加器
 * @create 2020-11-17
 */
public class CounterActor extends UntypedAbstractActor {

    /**
     * 用于累加的数据
     */
    private int count = 0;

    /**
     * 数字类型就累加，其他类型就打印
     *
     * @param message 消息
     * @throws Throwable 异常
     */
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Number) {
            count += ((Number) message).intValue();
        } else {
            System.out.println(count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建actorSystem
        final ActorSystem system = ActorSystem.create("Counter");

        // 4个线程的线程池模拟
        final ExecutorService executor = Executors.newFixedThreadPool(4);

        // 创建CounterActor
        final ActorRef actorRef = system.actorOf(Props.create(CounterActor.class));

        // 四个线程开始发送消息
        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    actorRef.tell(1,ActorRef.noSender());
                }
            });
        }

        // 关闭线程池
        executor.shutdown();

        // 等待counter处理完成
        TimeUnit.SECONDS.sleep(1);

        // 打印counter结果
        actorRef.tell("",ActorRef.noSender());

        // 关闭actor系统
        system.terminate();
    }
}
