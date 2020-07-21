/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-16
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.collection.basic;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: 数组拷贝
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-16
 */
public class ArrayCopyInfo {

    public static void main(String[] args) {
        // 创建一个字符串类型
        String[] strs = new String[]{"a", "b", "c"};
        final String[] strings = Arrays.copyOf(strs, strs.length);
        Stream.of(strings)
                .reduce(ArrayCopyInfo::stringReduce)
                .ifPresent(System.out::println);
        // Integer 如果达到最大值，会发生什么？
        int max = Integer.MAX_VALUE;

        System.out.println((max + 1));
        System.out.println((max + 1) == Integer.MIN_VALUE);
        System.out.println((max + 2) == Integer.MIN_VALUE + 1);
        // Integer 4个字节 32位

        // OS 32位 (4个字节) 64位(8个字节)
        // 32位系统中 long , double 操作不是线程安全的，需要分成高低32位存储 , 64位操作系统是线程安全的
        // Java Integer默认是一个带符号数，没有无符号数
        // C unsigned int(size_t) 无符号数
    }

    private static String stringReduce(String other, String another) {
        return other + " " + another;
    }

}
