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
package org.fufeng.collection.advanced;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: jguid
 * @description: {@link Iterator} 迭代器
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-21
 */
public class IteratorModificationInfo {

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>(List.of(1,2,3));
        final Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()){
            // List.add(T) 方法在 next() 方法调用之前执行，会抛出 java.util.ConcurrentModificationException
            //values.add(4);
            // Integer.remove() 方法在 next() 方法调用之前执行，会抛出 java.lang.IllegalStateException
            //iterator.remove();
            // List.remove(int) 方法在 next() 方法调用之前执行，会抛出 java.util.ConcurrentModificationException
            //values.remove(0);
            Integer value = iterator.next();
            // [0] = 1, [1] = 2, [2] = 3
            // c -> 1
            // v = 1
            // [0] = 1, [1] = 2, [2] = 3, [3] = 4
            // c -> 2
            // v = 2
            // [0] = 1, [1] = 2, [2] = 3, [3] = 4,[4] = 4
            System.out.println(value);
            // 结论：Iterator 只能在 next() 方法执行后，通过 remove() 移除数据，也无法对源 Collection 对象操作
            //values.add(4);
        }
    }

}
