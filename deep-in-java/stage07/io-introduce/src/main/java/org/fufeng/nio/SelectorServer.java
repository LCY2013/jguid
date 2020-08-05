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
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @program: jguid
 * @description: 选择器套接字编程
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 */
public class SelectorServer {

    public static void main(String[] args) {
        // 服务端ServerSocket
        try (final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
            // 绑定端口
            serverSocketChannel.bind(new InetSocketAddress(8846));
            // 设置非阻塞
            serverSocketChannel.configureBlocking(false);
            System.out.println("当前服务器地址:"+serverSocketChannel.socket().getLocalSocketAddress());
            // 打开Selector
            final Selector selector = Selector.open();
            // 注册OP_ACCEPT
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true){
                int eventNum = selector.select();
                if (eventNum == 0){
                    continue;
                }
                final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                final Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                if (selectionKeyIterator.hasNext()){
                    final SelectionKey selectionKey = selectionKeyIterator.next();
                    if (selectionKey.isAcceptable()){
                        ServerSocketChannel ssc = (ServerSocketChannel)selectionKey.channel();
                        final SocketChannel accept = ssc.accept();
                        if (Objects.isNull(accept)){
                            continue;
                        }
                        System.out.printf("接受来自客户端[%s]\n",accept.getRemoteAddress());
                        // 生成一个数据buffer
                        final ByteBuffer byteBuffer = ByteBuffer.allocate(16);
                        // 将服务器的时间戳传递给客户端
                        byteBuffer.putLong(System.currentTimeMillis());
                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()){
                            accept.write(byteBuffer);
                        }
                        byteBuffer.clear();
                        accept.close();
                        System.out.println("服务器时间戳已经发送给服务器");
                    }
                    // 移除遍历后的选择器
                    selectionKeyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
