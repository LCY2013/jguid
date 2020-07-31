/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-31
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.beans.event;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: 事件 {@link ApplicationEvent} 广播器
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-07-31
 * @see ApplicationEvent 事件
 * @see ApplicationEventListener 事件监听器
 * @see ApplicationEventListenerRegistry 事件监听器注册中心
 */
public class ApplicationEventMulticaster {

    // 事件监听器注册中心设置
    private final ApplicationEventListenerRegistry eventListenerRegistry;

    // 异步执行器
    private ExecutorService executorService;

    // 是否开启异步
    private volatile boolean async;

    /**
     * 普通构造方法
     */
    public ApplicationEventMulticaster() {
        this.eventListenerRegistry = new GenericApplicationEventListenerRegistry();
    }

    /**
     * 设置异步执行器
     *
     * @param executorService 异步任务执行器
     */
    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * 是否开启异步
     *
     * @param async 开启异步
     */
    public void setAsync(boolean async) {
        this.async = async;
        if (async && Objects.isNull(this.executorService)) {
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), new EventExecutorThreadFactory());
        }
    }

    /**
     * 构造一个发布器
     *
     * @param eventListenerRegistry 监听器注册中心
     */
    public ApplicationEventMulticaster(ApplicationEventListenerRegistry eventListenerRegistry) {
        this.eventListenerRegistry = eventListenerRegistry;
    }

    /**
     * 注册事件监听器
     *
     * @param applicationEventListener 监听器容器实现
     * @see ApplicationEventListenerRegistry#addApplicationEventListener 添加监听器
     * @see GenericApplicationEventListenerRegistry#addApplicationEventListener(ApplicationEventListener) 唯一实现
     */
    public void addApplicationEventListener(ApplicationEventListener<?> applicationEventListener) {
        eventListenerRegistry.addApplicationEventListener(applicationEventListener);
    }

    /**
     * 移除事件监听
     *
     * @param applicationEventListener 监听器容器实现
     * @see ApplicationEventListenerRegistry#removeApplicationEventListener(ApplicationEventListener) 移除事件监听器
     * @see GenericApplicationEventListenerRegistry#removeApplicationEventListener(ApplicationEventListener) 唯一实现
     */
    public void removeApplicationEventListener(ApplicationEventListener<?> applicationEventListener) {
        eventListenerRegistry.removeApplicationEventListener(applicationEventListener);
    }

    /**
     * 获取所有的事件监听器
     *
     * @return 所有事件监听器
     * @see ApplicationEventListenerRegistry#getApplicationEventListeners() 获取所有事件监听器
     * @see GenericApplicationEventListenerRegistry#getApplicationEventListeners() 事件监听器获取唯一实现
     */
    public ApplicationEventListener<? extends ApplicationEvent>[] getApplicationEventListeners() {
        return eventListenerRegistry.getApplicationEventListeners();
    }

    /**
     * 根据事件类型获取所有的事件监听器
     *
     * @param eventType 事件类型
     * @return 所有的事件监听器
     * @see ApplicationEventListenerRegistry#getApplicationEventListeners(Class) 根据某个事件类型获取事件监听器
     * @see GenericApplicationEventListenerRegistry#getApplicationEventListeners(Class) 唯一实现
     */
    public ApplicationEventListener<? extends ApplicationEvent>[] getApplicationEventListeners(Class<? extends ApplicationEvent> eventType) {
        return eventListenerRegistry.getApplicationEventListeners(eventType);
    }

    /**
     * 事件发布方法
     *
     * @param event 事件
     * @see ApplicationEvent 事件定义
     */
    public void multicast(ApplicationEvent event) {
        // 逐一获取所有事件监听器
        Class<? extends ApplicationEvent> eventType = event.getClass();
        // 定义获取的事件监听器集合
        ApplicationEventListener[] applicationEventListeners =
                getApplicationEventListeners(eventType);
        // 逐一传递
        Stream.of(applicationEventListeners)
                .forEach(applicationEventListener -> {
                    if (this.async) {
                        this.executorService.execute(() ->
                                applicationEventListener.onEvent(event)
                        );
                    } else {
                        applicationEventListener.onEvent(event);
                    }
                });

        /*for (ApplicationEventListener listener : getApplicationEventListeners()){
            listener.onEvent(event);
        }*/
    }

    /**
     *  关闭相关资源
     */
    public void close(){
        synchronized (ApplicationEventMulticaster.class){
            if (Objects.nonNull(this.executorService)){
                this.executorService.shutdown();
            }
        }
    }

    public static void main(String[] args) {
        displaySimpleEvent();
        //displayGenericEvent();
    }

    private static void displayGenericEvent() {
        // 定义一个事件发布器
        ApplicationEventMulticaster multicaster =
                new ApplicationEventMulticaster(new GenericApplicationEventListenerRegistry());
        // 添加两个监听器
        multicaster.addApplicationEventListener(new CustomRefreshedEventListener());
        multicaster.addApplicationEventListener(new CustomStartedEventListener());

        // 发送一个刷新事件
        multicaster.multicast(new CustomRefreshedEvent("refreshed"));
    }

    private static void displaySimpleEvent() {
        // 创建一个事件发布器
        ApplicationEventMulticaster multicaster = new ApplicationEventMulticaster();
        // 开启异步
        multicaster.setAsync(true);
        // 注册三个监听事件
        multicaster.addApplicationEventListener((CustomRefreshedEvent event) -> System.out.printf("Thread-[%s],listener-1 recover event [%s] \n",Thread.currentThread().getName(),event));
        multicaster.addApplicationEventListener(event -> System.out.printf("Thread-[%s],listener-2 recover event [%s] \n",Thread.currentThread().getName(),event));
        multicaster.addApplicationEventListener(event -> System.out.printf("Thread-[%s],listener-3 recover event [%s] \n",Thread.currentThread().getName(),event));
        // 广播事件
        multicaster.multicast(new ApplicationEvent("fufeng"));
        multicaster.multicast(new CustomRefreshedEvent("refreshed"));
        // 关闭广播器
        multicaster.close();
    }
}
