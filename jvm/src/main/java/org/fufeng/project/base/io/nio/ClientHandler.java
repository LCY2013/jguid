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
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 客户端处理器
 * @create 2020-12-07
 */
public class ClientHandler implements Runnable {

    /**
     * 双端通道
     */
    private SocketChannel socketChannel;
    /**
     * 端口号
     */
    private int port;
    /**
     * 选择器
     */
    private Selector selector;
    /**
     * 主机地址
     */
    private String host;
    /**
     * 是否已经停止
     */
    private boolean stop;

    public ClientHandler(int port, String host) {
        this.port = port;
        this.host = host;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            // 设置为非阻塞
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // 连接服务器
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!stop) {
            try {
                selector.select(1000);
                final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    handleInput(key);
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
     * 处理输入
     *
     * @param key 选择key
     */
    private void handleInput(SelectionKey key) throws IOException {
        // 校验key是否合法
        if (key.isValid()) {
            // 获取连接的通道信息
            SocketChannel socketChannel = (SocketChannel) key.channel();
            // 是否是连接事件
            if (key.isConnectable()) {
                if (!socketChannel.finishConnect()) {
                    System.exit(-1);
                } else {
                    // 注册读事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
            }
            // 判断是否是读事件
            if (key.isReadable()) {
                final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                final int read = socketChannel.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    final byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.printf("现在时间 : %s\n", body);
                    //this.stop = true;
                } else if (read < 0) {
                    key.cancel();
                    socketChannel.close();
                }
            }
        }
    }

    /**
     * 连接服务器
     */
    private void doConnect() throws IOException {
        // 连接服务器端
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            // 连接成功，注册读事件
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            // 连接失败，注册连接事件
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    /**
     * 发送消息给客户端
     *
     * @param msg 消息
     */
    public void sendMsg(String msg) throws IOException {
        doWrite(socketChannel, msg);
        // 重新注册读事件
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    /**
     * 发送行为
     *
     * @param socketChannel 连接通道
     * @param msg           消息
     */
    private void doWrite(SocketChannel socketChannel, String msg) throws IOException {
        final byte[] bytes = msg.getBytes();
        final ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        if (!byteBuffer.hasRemaining()) {
            System.out.printf("客户端发送消息成功: %s\n", msg);
        }
    }
}
