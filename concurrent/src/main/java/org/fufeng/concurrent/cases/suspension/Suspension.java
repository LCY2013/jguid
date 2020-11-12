/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-12
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.suspension;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description Guarded Suspension 模式(保护性暂停)
 * <p>
 * message(请求业务)
 * ->  MQ   ->
 * process message(处理业务)
 * <-  MQ   <-
 * message receiver
 * @create 2020-11-12
 */
public class Suspension {

    /**
     * 模拟请求mq
     */
    private static BlockingQueue<RpcRequest> reqMq = new ArrayBlockingQueue<>(10);
    /**
     * 模拟响应mq
     */
    private static BlockingQueue<RpcResponse> resMq = new ArrayBlockingQueue<>(10);

    /**
     * 请求消息id号
     */
    private static final AtomicLong reqIds = new AtomicLong(0);

    public static void main(String[] args) {

        // 模拟启动两个线程去处理请求和响应消息
        handRequestMessage();
        handResponseMessage();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                // 发送异步的mq消息
                final RpcRequest rpcRequest = new RpcRequest(reqIds.getAndIncrement(), "send message to mq");
                sendRequestMessageToMQ(rpcRequest);

                // 测试Guarded Suspension 模式
                // 保存GuardedObject
                final GuardedObject<RpcResponse> guardedObject = GuardedObject.newGuardedObject(rpcRequest.getRequestId());

                // 等待返回值
                final RpcResponse response = guardedObject.get(Objects::nonNull);
                print(response);

            }).start();
        }
    }

    /**
     * 发送业务请求消息
     *
     * @param message rpc 请求消息
     */
    private static void sendRequestMessageToMQ(RpcRequest message) {
        //print(message);
        reqMq.add(message);
        /*new Thread(() -> {
            // 假如这里服务端已经处理完成
            sendResponseMessageToMQ(new RpcResponse(message.getRequestId(),200,
                    "success",null));
        }).start();*/
    }

    /**
     *  处理请求消息
     */
    private static void handRequestMessage() {
        new Thread(() -> {
            while (true) {
                try {
                    final RpcRequest request = reqMq.take();
                    // 假如这里服务端已经处理完成
                    sendResponseMessageToMQ(new RpcResponse(request.getRequestId(),200,
                            "success",null));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 发送业务处理完成消息
     *
     * @param message 消息
     */
    private static void sendResponseMessageToMQ(RpcResponse message) {
        //print(message);

        resMq.add(message);

       /* // 模拟处理时间
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() ->
                GuardedObject.fireEvent(message.getReqId(),message)
        ).start();*/
    }

    /**
     *  处理响应消息
     */
    private static void handResponseMessage() {
        new Thread(() -> {
            while (true) {
                try {
                    final RpcResponse response = resMq.take();
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    GuardedObject.fireEvent(response.getReqId(),response);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private static void print(Object message) {
        System.out.printf("[%s],%s\n", Thread.currentThread().getName(), message);
    }
}
