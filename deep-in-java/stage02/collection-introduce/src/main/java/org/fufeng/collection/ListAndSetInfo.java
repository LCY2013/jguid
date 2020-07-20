/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-20
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: jguid
 * @description: {@link Set} 和 {@link List} 两个重要的集合概念
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-20
 */
public class ListAndSetInfo {

    public static void main(String[] args) {
        // HashSet 不能够保证顺序
        HashSet<String> values = new HashSet<>();
        // 字符
        values.add("a");
        values.add("b");
        values.add("c");
        values.forEach(System.out::println);

        // 数字
        values.clear();
        values.add("1");
        values.add("2");
        values.add("3");
        values.forEach(System.out::println);
        // ASCII 码

        // 以上例子是 ASCII 码
        // HashSet 或者 HashMap 采用对象 HashCode
        // String hashCode 由 char[] 数组构建
        /*
         *    public static int hashCode(byte[] value) {
         *         int h = 0;
         *         for (byte v : value) {
         *             h = 31 * h + (v & 0xff);
         *         }
         *         return h;
         *     }
         */
        // 在 Java 中 char（2字节） 相当于 int（4字节）
        // 汉字通过 2个 char（4字节），用一个 int（4字节）

    }

    // 一致性 Hash 1 2 3
    // 3000 的请求，平均每个节点是一个 1000 个请求
    // 当 节点 1 失效，Key 1 就尝试 2 或 3
}
