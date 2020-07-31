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

/**
 * @program: jguid
 * @description: {@link ApplicationEvent} 事件监听器注册
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-31
 * @see ApplicationEvent 事件
 * @see ApplicationEventListener 事件监听器
 */
public interface ApplicationEventListenerRegistry {

    /**
     *  添加监听器注册
     * @param applicationEventListener ApplicationEvent监听器
     */
    void addApplicationEventListener(ApplicationEventListener<?> applicationEventListener);

    /**
     *  移除监听器注册
     * @param applicationEventListener ApplicationEvent监听器
     */
    void removeApplicationEventListener(ApplicationEventListener<?> applicationEventListener);

    /**
     *  获取所有的事件监听器
     * @return 事件监听器集合
     */
    ApplicationEventListener<? extends ApplicationEvent>[] getApplicationEventListeners();

    /**
     *  获取某个类型的事件监听器
     * @param eventType 事件类型
     * @return 某个类型的事件监听器集合
     */
    ApplicationEventListener<? extends ApplicationEvent>[] getApplicationEventListeners(Class<? extends ApplicationEvent> eventType);
}
