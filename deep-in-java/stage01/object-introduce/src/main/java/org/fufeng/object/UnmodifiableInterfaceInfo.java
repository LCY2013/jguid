/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-14
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @program: jguid
 * @description: {@link java.util.stream.Stream}
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-14
 */
public class UnmodifiableInterfaceInfo {

    public static void main(String[] args) {
        // 创建一个集合类,可修改的集合信息
        final Collection<Integer> values = of(1, 2, 3, 4, 5, 6);
        values.add(7);
        System.out.println(values);

        //创建一个不可修改的集合信息
        final Collection<Integer> unmodifiableValue = unmodifiable(1, 2, 3, 4, 5);
        //不允许修改
        //数据读写都是一致的
        //只读代表它是线程安全
        unmodifiableValue.add(6);
    }

    public static Collection<Integer> of(Integer... values){
        return new ArrayList<>(Arrays.asList(values));
    }

    public static Collection<Integer> unmodifiable(Integer... values){
        return Collections.unmodifiableList(Arrays.asList(values));
    }
}
