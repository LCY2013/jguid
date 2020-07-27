/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-24
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.concurrency.container;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: jguid
 * @description: {@link ConcurrentHashMap} 并发安全的Map容器
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-24
 * @see ConcurrentHashMap
 */
public class ConcurrentHashMapInfo {

    public static void main(String[] args) {
        /*
            ConcurrentHashMap 使用场景，读多写少
            key 和 value 都不允许为null
            锁情况 :
              写 :
                < 1.8 分段锁
                > 1.8 保留分段锁设计，只是做兼容，主要是关键桶位置加上Synchronized 或者 CAS 保证线程安全
              读 :
                < 1.8 无锁
                > 1.8 无锁

             容量必须是2^n 这种形式,其目的如下:
                1、保证扩容后可以直接定位该元素的位置，要么在index，要么在2^n+index上
                2、保证计算hash值的时候，可以利用位运算符代替取余%运算
             保证容量是2^n次方的函数方法(java.util.concurrent.ConcurrentHashMap.tableSizeFor())
         */
        ConcurrentHashMap<String,Object> chm =
                new ConcurrentHashMap<>();
    }

}
