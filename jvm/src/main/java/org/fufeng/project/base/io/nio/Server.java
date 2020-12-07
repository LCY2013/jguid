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

import java.util.Objects;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description nio 启动类
 * @create 2020-12-07
 */
public class Server {

    /**
     * 定义默认端口号
     */
    private final static int DEFAULT_PORT = 8080;

    /**
     * nio 处理器
     */
    private static ServerHandler serverHandler;

    /**
     *  启动程序
     */
    public static void start() {
        start(DEFAULT_PORT);
    }

    /**
     *  自定义端口启动程序
     * @param port 启动端口
     */
    private static synchronized void start(int port) {
        // 优先判断是否已经存在服务
        if (Objects.nonNull(serverHandler)){
            serverHandler.stop();
        }
        serverHandler = new ServerHandler(port);
        new Thread(serverHandler,"NioServer").start();
    }

    public static void main(String[] args) {
        Server.start();
    }

}
