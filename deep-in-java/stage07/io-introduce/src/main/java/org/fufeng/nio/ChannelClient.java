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
import java.nio.channels.SocketChannel;

/**
 * @program: jguid
 * @description: {@link }
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 */
public class ChannelClient {

    // select 和 epoll
    public static void main(String[] args) {
        // 客户端SocketChannel
        try (final SocketChannel socketChannel = SocketChannel.open()){
            // 设置非阻塞
            socketChannel.configureBlocking(false);
            // 设置连接服务器端口
            socketChannel.connect(new InetSocketAddress(8846));
            while (!socketChannel.finishConnect()){
                System.out.println("connecting...");
            }
            final ByteBuffer byteBuffer = ByteBuffer.allocate(16);
            // 开始读取套接字信息
            while (socketChannel.read(byteBuffer) != -1){
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    System.out.println((char)byteBuffer.get());
                }
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
