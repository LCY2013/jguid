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
package org.fufeng.concurrent.cases.local;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 自定义 ThreadLocal，演示ThreadLocal原理
 * @create 2020-11-12
 * @see ThreadLocal
 */
public class CustomThreadLocal<T> {

    /**
     * 定义保存自定义线程值的容器
     */
    private final Map<Thread, Map<CustomThreadLocal<T>, T>> threadValueMap = new ConcurrentHashMap<>();

    /**
     * 通过自定义CustomThreadLocal获取需要的对象信息
     *
     * @return 需要的对象信息
     */
    public T get() {
        Thread t = Thread.currentThread();
        // 模拟从map容器中获取数据
        final Map<CustomThreadLocal<T>, T> map = this.threadValueMap.get(t);
        // 如果该值存在就进行返回
        if (Objects.nonNull(map)) {
            return map.get(this);
        }
        // 设置初始化值，然后返回
        return setInitialValue();
    }

    /**
     * 设置值
     *
     * @param value 值
     */
    public void set(T value) {
        Thread t = Thread.currentThread();
        final Map<CustomThreadLocal<T>, T> threadLocalTMap = this.threadValueMap.get(t);
        if (Objects.nonNull(threadLocalTMap)) {
            threadLocalTMap.put(this, value);
        } else {
            final Map<CustomThreadLocal<T>, T> map = new ConcurrentHashMap<>();
            map.put(this, value);
            threadValueMap.put(Thread.currentThread(), map);
        }
    }

    /**
     * 移除当前线程存在的值信息
     */
    public void remove() {
        final Map<CustomThreadLocal<T>, T> threadLocalTMap =
                this.threadValueMap.get(Thread.currentThread());
        threadLocalTMap.remove(this);
    }

    /**
     * 设置初始化值，然后返回
     *
     * @return 是否存在初始化值的设置，如果存在就通过初始化的Supplier返回
     * @see Supplier
     */
    private T setInitialValue() {
        // 调用初始化方法
        T value = initialValue();
        if (Objects.nonNull(value)) {
            final Map<CustomThreadLocal<T>, T> map = new ConcurrentHashMap<>();
            map.put(this, value);
            threadValueMap.put(Thread.currentThread(), map);
        }
        return value;
    }

    /**
     * 自定义初始化ThreadLocal逻辑
     *
     * @param supplier 函数模式
     * @param <S>      根据线程存储的对象信息
     * @return 自定义的CustomThreadLocal
     * @see {@link CustomThreadLocal}
     */
    public static <S> CustomThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
        return new SuppliedThreadLocal<>(supplier);
    }

    /**
     * 封装一个具体初始化CustomThreadLocal的子类
     *
     * @param <T> 需要通过CustomThreadLocal获取的类型
     */
    static final class SuppliedThreadLocal<T> extends CustomThreadLocal<T> {

        private final Supplier<? extends T> supplier;

        SuppliedThreadLocal(Supplier<? extends T> supplier) {
            this.supplier = Objects.requireNonNull(supplier);
        }

        @Override
        protected T initialValue() {
            return supplier.get();
        }
    }

    /**
     * 默认初始化操作
     *
     * @return 初始化对象
     */
    protected T initialValue() {
        return null;
    }

    public static void main(String[] args) {
        final CustomThreadLocal<SimpleDateFormat> customThreadLocal =
                CustomThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                final SimpleDateFormat simpleDateFormat = customThreadLocal.get();
                print(simpleDateFormat, simpleDateFormat.format(new Date()));
            }).start();
        }
    }

    private static void print(Object sdf, String message) {
        System.out.printf("[%s],%s,%s\n", Thread.currentThread().getName(), System.identityHashCode(sdf), message);
    }
}
