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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 利用读写锁(ReadWriteLock)实现一个缓存
 * @create 2020-11-06
 */
public class CacheV1<K, V> {

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
     *  获取缓存数据
     *
     * @param key 缓存key
     * @return 缓存value
     */
    public V get(K key) {
        readLock.lock();
        try {
            return cacheMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    /**
     *  设置数据到缓存中
     *
     * @param key 缓存key
     * @param value 缓存value
     */
    public void set(K key, V value) {
        writeLock.lock();
        try {
            cacheMap.put(key,value);
        }finally {
            writeLock.unlock();
        }
    }

}
