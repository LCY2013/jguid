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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description bio 服务端
 * @create 2020-12-07
 */
public class Server {

    /**
     * 默认端口
     */
    private final static int DEFAULT_PORT = 8080;

    /**
     * 服务端socket通道
     */
    private static ServerSocket serverSocket;

    /**
     * 线程池定义
     */
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 定义tcp服务端启动方法
     */
    public static void start() {
        start(DEFAULT_PORT);
    }

    /**
     * 定义自定义端口启动方法
     *
     * @param port 端口
     */
    private static synchronized void start(int port) {
        // 如果服务端serverSocket没有启动成功
        if (Objects.nonNull(serverSocket)) {
            return;
        }
        try {
            // 启动服务
            serverSocket = new ServerSocket(port);
            System.out.printf("服务器已经启动，端口号 : %d \n",port);
            // 循环监听客户端连接
            while (true){
                Socket socket = serverSocket.accept();
                // 交由子线程处理客户端连接
                executorService.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(serverSocket)){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        // 执行服务器tcp
        new Thread(Server::start).start();
    }

}
