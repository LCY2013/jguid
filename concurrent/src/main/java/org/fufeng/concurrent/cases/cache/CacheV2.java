/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-06
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.cache;

import sun.java2d.pipe.ValidatePipe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 利用读写锁(ReadWriteLock)实现一个按需缓存
 * @create 2020-11-06
 */
public class CacheV2<K, V> {

    /**
     * 定义缓存容器
     * K 容器key
     * V 容器value
     */
    private final Map<K, V> cacheMap = new HashMap<>();

    /**
     * 定义读写锁相关
     */
    private final ReadWriteLock rw = new ReentrantReadWriteLock();

    /**
     * 读锁
     */
    private final Lock readLock = rw.readLock();

    /**
     * 写锁
     */
    private final Lock writeLock = rw.writeLock();

    /**
     * 缓存数据源注入
     */
    private final DBUtils dbUtils = new DBUtils();

    /**
     *  是否校验缓存信息
     */
    private volatile boolean cacheValid;

    /**
     * 获取缓存数据
     *
     * @param key 缓存key
     * @return 缓存value
     */
    public V get(K key) {
        // 定义查询缓存返回的数据
        V value = null;

        // 先利用读锁查询缓存信息
        readLock.lock();
        try {
            value = cacheMap.get(key);
        } finally {
            readLock.unlock();
        }

        // 如果缓存不为null,就可以直接返回
        if (value != null) {
            return value;
        }

        // 如果缓存为null，就需要去数据源里面查询该key对应的value
        writeLock.lock();
        try {
            // 再去数据库查询一次，防止被其他线程加载进了缓存
            value = cacheMap.get(key);

            // 这个时候如果还是没有在缓存中查询到数据，就需要去数据源中查询数据
            if (value == null) {
                value = (V) dbUtils.get(value.getClass());
            }
            // 如果value在数据源查询到数据
            if (Objects.nonNull(value)) {
                cacheMap.put(key,value);
            }
        } finally {
            writeLock.unlock();
        }
        return value;
    }

    /**
     * 设置数据到缓存中
     *
     * @param key   缓存key
     * @param value 缓存value
     */
    public void set(K key, V value) {
        writeLock.lock();
        try {
            cacheMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     *  错误示例
     *  ReadWriteLock 不支持锁升级
     *
     *  读锁还没有释放，此时获取写锁，会导致写锁永久等待，最终导致相关线程都被阻塞，永远也没有机会被唤醒。
     */
    public void errorCase(){
        readLock.lock();
        try {
            writeLock.lock();
            try {
                System.out.println("读写锁升级");
            }finally {
                writeLock.unlock();
            }
        }finally {
            readLock.unlock();
        }
    }

    /**
     *  ReadWriteLock 支持锁降级
     *
     */
    public void normalCase(){
        // 先用读锁控制
        readLock.lock();
        if (!cacheValid) {
            // 读锁解锁，防止锁升级，避免永久阻塞
            readLock.unlock();

            // 写锁
            writeLock.lock();
            try {
                System.out.println("process data ...");
                // 数据处理完，进行写锁降级到读锁
                readLock.lock();
            }finally {
                // 释放写锁
                writeLock.unlock();
            }
        }

        try {
            // 这里持有读锁
            System.out.println("have read lock ...");
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        CacheV2<Long,String> cacheV2 = new CacheV2<>();
        //cacheV2.errorCase();
        cacheV2.normalCase();
    }

}
