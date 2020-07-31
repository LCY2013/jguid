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

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @program: jguid
 * @description: 简单 {@link ApplicationEventListener 应用事件监听器}注册中心实现
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-07-31
 */
public class SimpleApplicationEventListenerRegistry implements ApplicationEventListenerRegistry {

    private Set<ApplicationEventListener<?>> listeners = new TreeSet<>(new ApplicationEventListenerComparator());


    @Override
    public void addApplicationEventListener(ApplicationEventListener<?> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeApplicationEventListener(ApplicationEventListener<?> listener) {
        listeners.remove(listener);
    }

    /**
     * @return 只读的 {@link ApplicationEventListener} 列表
     */
    @Override
    public ApplicationEventListener[] getApplicationEventListeners() {
        return listeners.toArray(new ApplicationEventListener[0]);
    }

    @Override
    public ApplicationEventListener[] getApplicationEventListeners(Class<? extends ApplicationEvent> eventType) {
        return new ApplicationEventListener[0];
    }

    /**
     * 通过 {@link ApplicationEventListener} 实现类去重
     */
    static class ApplicationEventListenerComparator implements Comparator<ApplicationEventListener> {

        @Override
        public int compare(ApplicationEventListener o1, ApplicationEventListener o2) {
            String oneClassName = o1.getClass().getName();
            String anotherClassName = o2.getClass().getName();
            return oneClassName.compareTo(anotherClassName);
        }
    }
}