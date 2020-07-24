/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-23
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.concurrency.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @program: jguid
 * @description: Synchronized 性能测试
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-23
 */
public class SynchronizedPerformanceInfo {

    public static void main(String[] args) {
        // 对比 Vector 与 ArrayList
        // 上面两个容器基本底层数据结构都是数组，主要差异在同步
        final Vector<Object> vector = new Vector<>();
        final ArrayList<Object> list = new ArrayList<>();

        // 开始基准测试
        benchmark(1000,vector);
        benchmark(1000,list);
        System.gc();
        System.out.println();
        benchmark(10000,vector);
        benchmark(10000,list);
        System.gc();
        System.out.println();
        benchmark(100000,vector);
        benchmark(100000,list);
        System.gc();
        System.out.println();
        benchmark(500000,vector);
        benchmark(500000,list);
        System.gc();
        System.out.println();
        benchmark(1000000,vector);
        benchmark(1000000,list);
    }

    /**
     * 基准测试
     *
     * @param count 执行次数
     * @param list  容器模型
     */
    private static void benchmark(int count, List<Object> list) {
        long starTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.add(new Object());
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("%s add costing %f ms(avg)\n",
                list.getClass().getSimpleName(),
                (endTime - starTime)/(count*1.0));
    }

}
