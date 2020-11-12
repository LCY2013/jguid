/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-12
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.conw;

import java.util.Objects;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 定义路由信息
 * @create 2020-11-12
 */
public final class Router {

    /**
     *  接口名称
     */
    private final String interfaceName;

    /**
     *  服务端口
     */
    private final String ip;

    /**
     *  服务端口
     */
    private final int port;

    public Router(String interfaceName, String ip, int port) {
        this.interfaceName = interfaceName;
        this.ip = ip;
        this.port = port;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    // 重写hashcode和equals方法用于存在hash结构的容器进行比较
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Router router = (Router) o;
        return port == router.port &&
                Objects.equals(interfaceName, router.interfaceName) &&
                Objects.equals(ip, router.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interfaceName, ip, port);
    }
}
