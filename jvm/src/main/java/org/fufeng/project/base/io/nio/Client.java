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
import java.util.Scanner;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description nio client实现
 * @create 2020-12-07
 */
public class Client {

    /**
     * 默认主机地址
     */
    private static String DEFAULT_HOST = "127.0.0.1";

    /**
     * 默认端口号信息
     */
    private static int DEFAULT_PORT = 8080;

    /**
     * 客户端处理器
     */
    private static ClientHandler clientHandler;

    /**
     * 启动
     */
    public static void start() {
        start(DEFAULT_HOST, DEFAULT_PORT);
    }

    /**
     * 启动客户端服务
     *
     * @param host 主机信息
     * @param port 端口信息
     */
    private static void start(String host, int port) {
        clientHandler = new ClientHandler(port, host);
        new Thread(clientHandler, "Client").start();
    }

    /**
     * 向服务端发送消息
     *
     * @param msg 消息
     * @return 是否成功 {@code true} 成功 {@code false} 失败
     */
    public static boolean sendMsg(String msg) throws IOException {
        clientHandler.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) throws IOException {
        // 启动客户端
        Client.start();
        while (Client.sendMsg(new Scanner(System.in).nextLine())) ;
    }

}
