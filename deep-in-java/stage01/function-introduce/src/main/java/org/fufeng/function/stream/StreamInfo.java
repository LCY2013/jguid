/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-15
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.function.stream;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: {@link Stream} 流式API
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-15
 */
public class StreamInfo {

    public static void main(String[] args) {
        // 计算总和
        sum(1,2,3,4,5);
        line();
        // 排序
        sort(4,3,6,7,2,7,9,3,0,1);
        line();
        //按指定条件排序
        sort((x,y)-> y-x,0,8,6,4,3,5,1);
        line();
        // 并行排序
        parallelSort(2,1,4,5,8,7,5,6,8,9);
    }

    // 按指定条件排序
    private static void sort(Comparator<Integer> comparator,Integer... numbers){
        Stream.of(numbers)
                .sorted(comparator)
                .map(integer -> integer + " ")
                .forEach(System.out::print);
    }

    // 多线程排序
    private static void parallelSort(Integer... numbers){
        Stream.of(numbers).sorted().parallel().map(integer -> integer+" ").forEach(StreamInfo::print);
    }

    // 单线程排序
    private static void sort(Integer... numbers){
        Stream.of(numbers).sorted().map(integer -> integer+" ").forEach(System.out::print);
    }

    // 计算数值的总和
    private static void sum(Integer... numbers){
        Stream.of(numbers)
                .reduce(Integer::sum)
                .map(String::valueOf)
                .ifPresent(System.out::print);
    }

    private static void print(Object object){
        System.out.printf("[%s] : %s\n",Thread.currentThread().getName(),object);
    }

    // 换行
    private static void line(){
        System.out.println();
    }

}
