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

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 路由表设计
 * <p>
 * 使用 copy-on-write
 * <p>
 * 自动保存，在本地保存一份，让下次应用重启的时候注册中心宕机了，还可以读取本地的缓存数据，这也是服务降级的一种实现方案。
 * @create 2020-11-12
 */
public class RouterTableV1 {

    /**
     * 定义不同接口的路由表
     * key -> 接口名称
     * value -> 接口的所有路由信息
     */
    private final Map<String, CopyOnWriteArraySet<Router>> routerMap = new ConcurrentHashMap<>();

    /**
     * 当前使用一个线程的轮训线程池处理异步的存盘操作
     */
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    /**
     * 本地路由信息是否已经改变的标示位
     */
    private volatile boolean changed;

    /**
     * 利用jsr-250规范做应用初始化
     * <p>
     * 如spring 容器启动后的回调
     */
    @PostConstruct
    public void init() {
        // 每30秒扫描一下路由表信息是否已经改变
        executorService.scheduleWithFixedDelay(this::autoSave, 30, 30, TimeUnit.SECONDS);
        // 每20秒同步一下远程注册中心的信息
        executorService.scheduleWithFixedDelay(this::mergeRemoteRegisterRouterInfo, 0, 20, TimeUnit.SECONDS);
    }

    /**
     * 合并远程注册中心的路由信息
     */
    protected void mergeRemoteRegisterRouterInfo() {
        // 适配不同注册中心
        System.out.printf("[%s] 同步远程注册中心服务路由信息", Thread.currentThread().getName());
    }

    /**
     * 自动保存
     * <p>
     * Balking 模式
     */
    private void autoSave() {
        // 查看标示位是否发送改变
        if (!this.changed) {
            return;
        }
        // 执行保存操作
        saveToLocalDisk(routerMap);
    }

    /**
     * 向本地磁盘保存数据
     *
     * @param routerMap 路由信息
     */
    private void saveToLocalDisk(Map<String, CopyOnWriteArraySet<Router>> routerMap) {
        System.out.printf("[%s] 本地磁盘保存数据...\n", Thread.currentThread().getName());
    }

    /**
     * 新增某个接口的路由信息
     *
     * @param router 路由信息
     */
    public void addRouter(Router router) {
        // 条件判断
        if (Objects.isNull(router) || Objects.isNull(router.getInterfaceName())
                || Objects.isNull(router.getIp())) {
            throw new RuntimeException("router not allow param be null");
        }

        // 查询该接口是否存在路由，如果不存在就创建
        final CopyOnWriteArraySet<Router> routers =
                routerMap.computeIfAbsent(router.getInterfaceName(),
                        r -> new CopyOnWriteArraySet<>());
        routers.add(router);

        // 修改标示位
        this.changed = true;
    }

    /**
     * 新增某个接口的路由信息
     *
     * @param interfaceName 接口名称
     * @param ip            ip
     * @param port          端口
     */
    public void addRouter(String interfaceName, String ip, int port) {
        // 构建一个路由信息
        final Router router = new Router(interfaceName, ip, port);
        addRouter(router);
    }

    /**
     * 通过接口名称获取路由信息
     *
     * @param interfaceName 接口名称
     * @return 路由信息
     */
    public Collection<Router> getRouter(String interfaceName) {
        return routerMap.get(interfaceName);
    }

    /**
     * 移除某个接口的全部路由信息
     *
     * @param interfaceName 接口名称
     */
    public void removeRouters(String interfaceName) {
        routerMap.remove(interfaceName);
        // 修改标示位
        this.changed = true;
    }

    /**
     * 移除某个路由
     *
     * @param router 路由
     */
    public void removeRouter(Router router) {
        // 获取该路由的接口信息
        final CopyOnWriteArraySet<Router> routers =
                routerMap.get(router.getInterfaceName());
        // 移除某一个路由信息
        if (Objects.nonNull(routers) && routers.size() > 0) {
            // 通过重写路由对象的hashcode和equals方法直接移除该路由信息
            routers.remove(router);
            // 修改标示位
            this.changed = true;
        }
    }

}
