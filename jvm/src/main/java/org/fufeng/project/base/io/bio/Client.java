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
package org.fufeng.project.base.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description tcp客户端相关
 * @create 2020-12-07
 */
public class Client {

    /**
     * 默认端口
     */
    private final static int DEFAULT_PORT = 8080;

    /**
     * 默认ip地址
     */
    private final static String DEFAULT_IP = "127.0.0.1";

    /**
     * 客户端socket
     */
    private Socket socket;

    /**
     * 退出语意定义
     */
    private final static String QUIT = "quit";

    /**
     * 客户端连接方法
     */
    public void connect() {
        connect(DEFAULT_IP, DEFAULT_PORT);
    }

    /**
     * 连接服务端信息
     *
     * @param ip   ip
     * @param port 端口
     */
    public void connect(String ip, int port) {
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(ip, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     *
     * @param message 消息信息
     */
    public void sendMessage(String message) {
        System.out.printf("client  send message information about : %s\n", message);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println(message);
            System.out.printf("server received message : %s \n", in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭套接字
     */
    public synchronized void close() {
        if (Objects.nonNull(socket)) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 客户端执行
        new Thread(() -> {
            // 定义client
            final Client client = new Client();
            // 连接服务器
            client.connect();
            while (true) {
                String sendMessage = new Scanner(System.in).nextLine();
                if (!sendMessage.equals(QUIT)) {
                    // 客户端发送消息给服务端
                    client.sendMessage(sendMessage);
                } else {
                    client.close();
                    break;
                }
            }
        }).start();
    }

}
