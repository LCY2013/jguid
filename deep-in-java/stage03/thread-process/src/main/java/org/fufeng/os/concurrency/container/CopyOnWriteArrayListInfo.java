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

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: jguid
 * @description: {@link CopyOnWriteArrayList} 复制写数组集合
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-24
 * @see CopyOnWriteArrayList
 */
public class CopyOnWriteArrayListInfo {

    public static void main(String[] args) {
        // Diamond 语法
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList =
                new CopyOnWriteArrayList<>();

        // main 线程添加几个数据
        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(3);
        // Copy
        // JDK 两大核心性能提升
        // 1、数组
        // 2、String

        // 通过迭代器向集合中添加元素
        int times = 0;
        // 获取迭代器对象
        final Iterator<Integer> iterator = copyOnWriteArrayList.iterator();
        while (iterator.hasNext() && times < 100){
            iterator.next();
            copyOnWriteArrayList.add(times);
            times++;
        }

        System.out.println(copyOnWriteArrayList);
    }

}
