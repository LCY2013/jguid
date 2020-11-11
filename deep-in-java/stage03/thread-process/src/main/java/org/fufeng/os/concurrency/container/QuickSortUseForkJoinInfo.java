/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-27
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

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @program: jguid
 * @description: {@link ForkJoinPool} 窃取工作队列
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-27
 * @see ForkJoinPool
 * @see ForkJoinTask
 * @see RecursiveAction
 */
public class QuickSortUseForkJoinInfo {

    public static void main(String[] args) throws InterruptedException {

        Integer[] values = new Integer[]{2, 5, 6, 7, 8, 8, 9, 2, 1, 6, 7, 5, 6, 11, 23};

        ForkJoinPool pool = new ForkJoinPool();

        pool.submit(new QuickSortTask(values));

        pool.awaitTermination(100, TimeUnit.MILLISECONDS);

        pool.shutdown();

        System.out.println(Arrays.asList(values));
    }

    private static class QuickSortTask extends RecursiveAction {

        private static final long serialVersionUID = -8217430615611487747L;
        private final Integer[] parts;

        private final int low;

        private final int high;
        // 设置一个阈值
        private static final int THRESHOLD = 4;

        private QuickSortTask(Integer[] parts) {
            this.parts = parts;
            this.low = 0;
            this.high = parts.length - 1;
        }

        private QuickSortTask(Integer[] parts, int low, int high) {
            this.parts = parts;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low < THRESHOLD) { // 范围低于阈值时，直接排序
                sort(parts, low, high);
            } else {
                int pivot = partition(parts, low, high);
                QuickSortTask task = new QuickSortTask(parts, low, pivot - 1);
                QuickSortTask task2 = new QuickSortTask(parts, pivot + 1, high);
                task.fork().join();
                task2.fork().join();
            }
        }

        /**
         * 获取分区索引
         *
         * @param values 数组对象
         * @param low    低位索引
         * @param high   高位索引
         * @return 分区索引
         */
        int partition(Integer[] values, int low, int high) {
            // 获取 pivot = values[high]

            // [3, 1, 2, 5, 4]
            // pivot = 4
            //              -1
            // [0] = 3 < 4 (0)
            // [1] = 1 < 4 (1)
            // [2] = 2 < 4 (2)
            // [3] = 5 > 4 (3)
            // => [(3, 1, 2), (4), (5)]
            // pIndex = 3

            Integer pivot = values[high];
            int i = low;

            for (int j = low; j < high; j++) {
                if (values[j].compareTo(pivot) < 1) { // <=
                    Integer temp = values[i]; // 低位数据
                    values[i] = values[j]; // 低位数据获取高位数据
                    values[j] = temp;
                    i++; // -1 -> 0
                }
            }

            Integer temp = values[i];
            values[i] = values[high];
            values[high] = temp;

            return i;
        }

        private void sort(Integer[] parts, int low, int high) {
            Arrays.sort(parts, low, high + 1);
        }
    }

}
