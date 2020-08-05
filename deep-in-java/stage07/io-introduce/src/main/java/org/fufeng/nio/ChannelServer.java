/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-08-05
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @program: jguid
 * @description: {@link Channel}
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 * @see java.nio.channels.ServerSocketChannel
 */
public class ChannelServer {

    // select and epoll
    public static void main(String[] args) {
        try (final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
            // 绑定服务器端口
            serverSocketChannel.bind(new InetSocketAddress(8846));
            // 设置非阻塞
            serverSocketChannel.configureBlocking(true);
            // 打印当前使用的本机地址
            System.out.println("当前服务器地址:" + serverSocketChannel.socket().getLocalSocketAddress());
            String message = "hello, i'm fufeng!";
            final ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
            while (true){
                final SocketChannel socketChannel = serverSocketChannel.accept();
                if (Objects.nonNull(socketChannel)){
                    // 接受客户端连接的信息
                    System.out.printf("接受客户端[%s]的接入\n",socketChannel.getRemoteAddress());
                    byteBuffer.rewind();
                    // 通过套接字管道写出
                    socketChannel.write(byteBuffer);
                    byteBuffer.clear();
                } else {
                    // 当没有完成连接的时候就进行休眠
                    TimeUnit.MILLISECONDS.sleep(200);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
