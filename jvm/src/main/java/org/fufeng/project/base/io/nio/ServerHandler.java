/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-12-07
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.project.base.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description nio handler
 * @create 2020-12-07
 */
public class ServerHandler implements Runnable {

    /**
     * nio 多路复用选择器
     */
    private Selector selector;

    /**
     * 服务端socket通道
     */
    private ServerSocketChannel serverSocketChannel;

    /**
     * 是否停止标识符
     */
    private volatile boolean stop;

    /**
     * 初始化多路复用选择器，绑定端口
     *
     * @param port 端口
     */
    public ServerHandler(int port) {
        try {
            // 打开多路复用选择器
            selector = Selector.open();
            // 打开服务端通道
            serverSocketChannel = ServerSocketChannel.open();
            // 配置是否阻塞
            serverSocketChannel.configureBlocking(false);
            // 设置socket相关信息
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            // 将多路选择器注册到服务器通道,该通道用于接受连接信息
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.printf("服务开始监听：%d \n", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止服务
     */
    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                // 开始处理选择器上事件
                final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 迭代遍历选择器上的事件
                final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();
                        if (Objects.nonNull(key.channel())){
                            key.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Objects.nonNull(selector)) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理事件
     *
     * @param key 选择器key
     */
    public void handleInput(SelectionKey key) throws IOException {
        // 判断该key是否有效
        if (key.isValid()) {
            // 判断该key是否是连接事件
            if (key.isAcceptable()) {
                // 如果是连接事件，则注册读事件
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                final SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
            // 判断该key是否是读事件
            if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                // 定义一个buffer
                final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                // 非阻塞
                final int read = socketChannel.read(byteBuffer);
                // 读取到数据
                if (read > 0) {
                    // 固定buffer
                    final Buffer buffer = byteBuffer.flip();
                    // 创建一个buffer数组
                    byte[] bytes = new byte[buffer.remaining()];
                    // 读取字节流数据到字节数组
                    byteBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.printf("服务端收到的消息是: %s \n", body);
                    String currentTime = String.valueOf(new Date());
                    doWrite(socketChannel, currentTime);
                } else if (read < 0) {
                    // 取消该key
                    key.cancel();
                    // 关闭客户端tcp通道
                    socketChannel.close();
                }
            }
        }
    }

    /**
     * 服务端向客户端写数据
     *
     * @param socketChannel 数据通道
     * @param currentTime   当前时间
     */
    private void doWrite(SocketChannel socketChannel, String currentTime) throws IOException {
        // 条件校验
        if (Objects.nonNull(currentTime) && currentTime.trim().length() > 0) {
            final byte[] bytes = currentTime.getBytes();
            final ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            if (!byteBuffer.hasRemaining()) {
                System.out.printf("客户端发送消息成功: %s\n",currentTime);
            }
        }
    }
}
