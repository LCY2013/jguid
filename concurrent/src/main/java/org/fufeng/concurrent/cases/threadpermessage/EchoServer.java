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
package org.fufeng.concurrent.cases.threadpermessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description Thread-Per-Message 模式
 * 为每一个任务新建一个Thread，用于处理任务
 * <p>
 * 该例就是使用传统的网络编程，每个线程接受一个客户端请求任务。
 * <p>
 * 这个方案在目前 Java 领域知名度并不高，但是在其他编程语言里却叫得很响，
 * 例如 Go 语言、Lua 语言里的协程，本质上就是一种轻量级的线程。
 * 轻量级的线程，创建的成本很低，基本上和创建一个普通对象的成本相似；
 * 并且创建的速度和内存占用相比操作系统线程至少有一个数量级的提升，
 * 所以基于轻量级线程实现Thread-Per-Message 模式就完全没有问题了。
 * <p>
 * Java 语言目前也已经意识到轻量级线程的重要性了，OpenJDK 有个 Loom 项目，
 * 就是要解决 Java 语言的轻量级线程问题，在这个项目中，轻量级线程被叫做Fiber。
 * @create 2020-11-13
 */
public class EchoServer {

    private void openServer() {
        try {
            // nio ServerSocketChannel
            ServerSocketChannel serverSocketChannel =
                    ServerSocketChannel.open().bind(new InetSocketAddress(8080));

            while (true) {
                // 接受客户端连接
                final SocketChannel socketChannel = serverSocketChannel.accept();
                // 新建线程处理客户端请求
                new Thread(() -> {
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
                }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
