/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-21
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
 * @description: 冒泡排序
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-21
 */
public class BubbleSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] values) {
        /*
            Comparable.compareTo
            < return -1
            = return 0
            > return 1
        */
        int size = values.length;

        // 冒泡排序开始
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size-i-1; j++){
                // 第j与j+1的元素比较
                if(values[j].compareTo(values[j+1])>0){
                    T temp = values[j];
                    values[j] = values[j+1];
                    values[j+1] = temp;
                }
            }
            System.out.printf("第%d轮后:%s\n",(i+1), Arrays.toString(values));
        }
    }

    public static void main(String[] args) {
        System.out.println(" 一般情况 ");
        Integer[] integers = Sort.of(2, 1, 4, 6, 3, 9, 6, 7, 0);
        // java7 Diamond语法
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        bubbleSort.sort(integers);
        System.out.printf("排序后的结果:%s\n",Arrays.toString(integers));

        System.out.println(" 极端情况(完全逆序) ");
        integers = Sort.of(5,4,3,2,1);
        bubbleSort.sort(integers);
        System.out.printf("排序后的结果:%s\n",Arrays.toString(integers));
    }
}
