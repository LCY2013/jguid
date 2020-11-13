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

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.fufeng.concurrent.cases.threadpermessage.handler.HelloWorldHandler;
import org.fufeng.concurrent.cases.threadpermessage.infrastructure.LoomThreadPool;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description jetty server 利用协程
 * <p>
 * loom and standard in jvm start properties
 *
 * ab压测结果
 *
 * ab -n 1 -c 1 http://127.0.0.1:8080/
 *
 * Server Software:        Jetty(9.4.31.v20200723)
 * Server Hostname:        127.0.0.1
 * Server Port:            8080
 *
 * Document Path:          /
 * Document Length:        21 bytes
 *
 * Concurrency Level:      1
 * Time taken for tests:   0.925 seconds
 * Complete requests:      1
 * Failed requests:        0
 * Total transferred:      169 bytes
 * HTML transferred:       21 bytes
 * Requests per second:    1.08 [#/sec] (mean)
 * Time per request:       924.721 [ms] (mean)
 * Time per request:       924.721 [ms] (mean, across all concurrent requests)
 * Transfer rate:          0.18 [Kbytes/sec] received
 *
 * Connection Times (ms)
 *               min  mean[+/-sd] median   max
 * Connect:        0    0   0.0      0       0
 * Processing:   925  925   0.0    925     925
 * Waiting:      923  923   0.0    923     923
 * Total:        925  925   0.0    925     925
 *
 * @create 2020-11-13
 */
public class JettyServer {

    public static Server createLoomBasedServer(int port) {
        var server = new Server(new LoomThreadPool());

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);

        server.setConnectors(new Connector[]{connector});
        server.setHandler(new HelloWorldHandler());

        return server;
    }

    public static Server createThreadPoolBasedServer(int port) {
        var server = new Server(port);
        server.setHandler(new HelloWorldHandler());

        return server;
    }

    public static void main(String[] args) throws Exception {
        var port = 8080;
        Server server = null;
        //var serverMode = System.getenv("SERVER_MODE");
        var serverMode = System.getProperty("SERVER_MODE");

        if ("loom".equalsIgnoreCase(serverMode)) {
            server = createLoomBasedServer(port);
        } else if ("standard".equalsIgnoreCase(serverMode)) {
            server = createThreadPoolBasedServer(port);
        } else {
            throw new RuntimeException();
        }

        server.start();
        server.join();
    }

}
