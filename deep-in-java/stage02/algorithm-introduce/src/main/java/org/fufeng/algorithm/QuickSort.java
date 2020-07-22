/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-22
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.algorithm;

import java.util.Arrays;

/**
 * @program: jguid
 * @description: 快排序
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-22
 */
public class QuickSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] values) {
        int n = values.length;
        int low = 0;
        int high = n - 1;

        // [3, 1, 2, 5, 4]
        // pivot = 4
        // => [(3, 1, 2), (4), (5)]
        // pIndex = 3
        // [0...2] = (3, 1, 2)
        // [3] = 4
        // [4] = 5

        // [0...2] = (3, 1, 2)
        // pivot = 2
        // => [(1), (2) , (3)]
        // pIndex = 1
        // [0] = 1
        // [1] = 2(pivot)
        // [2] = 3

        // [0] = 1, [1] = 2, [2] = 3, [3] = 4, [4] = 5

        sort(values, low, high);
    }

    private void sort(T[] values, int low, int high) {
        if (low < high) {
            // 9 -> pIndex = 5
            int pIndex = partition(values, low, high);
            // [0..4]
            sort(values, low, pIndex - 1);
            sort(values, pIndex + 1, high);
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
    int partition(T[] values, int low, int high) {
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

        T pivot = values[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (values[j].compareTo(pivot) < 1) { // <=
                T temp = values[i]; // 低位数据
                values[i] = values[j]; // 低位数据获取高位数据
                values[j] = temp;
                i++; // -1 -> 0
            }
        }

        T temp = values[i];
        values[i] = values[high];
        values[high] = temp;

        return i;
    }

    public static void main(String[] args) {
        Integer[] values = Sort.of(2, 5, 6, 7, 8, 8, 9, 2, 1, 6, 7, 5, 6, 11, 23);
        Sort<Integer> sort = new QuickSort<>(); // Java 7 Diamond 语法
        sort.sort(values);
        System.out.println(Arrays.asList(values));
    }
}
