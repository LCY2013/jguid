/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-13
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.workerthread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description Worker Thread 模式
 * <p>
 * 阻塞队列做任务池，然后创建固定数量的线程消费阻塞队列中的任务
 * @create 2020-11-13
 */
public class EchoServer {

    /**
     * 创建一个50个线程池去处理客户端的请求
     */
    //private final ExecutorService executorService = Executors.newFixedThreadPool(50);
    private final ExecutorService executorService = new ThreadPoolExecutor(50, 500,
            30, TimeUnit.SECONDS,
            // 创建有界队列
            new ArrayBlockingQueue<>(1000),
            // 创建待名称的线程池
            r -> new Thread(r, String.format("echo - %d", r.hashCode())),
            // 根据不同业务选择不同的拒绝策略
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    private void openServer() {
        // nio ServerSocketChannel
        ServerSocketChannel serverSocketChannel = null;
        try {
            // nio ServerSocketChannel init
            serverSocketChannel =
                    ServerSocketChannel.open().bind(new InetSocketAddress(8080));

            while (true) {
                // 接受客户端连接
                final SocketChannel socketChannel = serverSocketChannel.accept();
                // 用线程池去处理客户端请求
                executorService.execute(() -> {
                    try {
                        // 读 Socket
                        ByteBuffer rb = ByteBuffer
                                .allocateDirect(1024);

                        socketChannel.read(rb);
                        // 模拟处理请求
                        Thread.sleep(2000);
                        // 写 Socket
                        ByteBuffer wb = (ByteBuffer) rb.flip();
                        socketChannel.write(wb);
                        // 关闭 Socket
                        socketChannel.close();
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
