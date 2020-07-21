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
package org.fufeng.collection.advanced;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @program: jguid
 * @description: {@link Collection} 集合移除问题
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-20
 */
public class CollectionRemoveInfo {

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

        //常规使用
        final Iterator<Integer> iterator = values.iterator();
        for (; iterator.hasNext(); ) {
            final Integer next = iterator.next();
            // 异常java.util.ConcurrentModificationException
            //values.remove(next);
            iterator.remove();
            System.out.println(values.size());
        }

        values = new ArrayList<>(List.of(7, 8, 9));
        int size = values.size();
        for (int i = 0; i < size; i++) {
            final Integer inxValue = values.get(0);
            values.remove(inxValue);
            System.out.println(values.size());
        }

        values = new ArrayList<>(List.of(1, 2, 3));
        size = values.size();
        for (int i = 0; i < size; i++) {
            values.remove(0);
        }

        // 存在问题 java.util.ConcurrentModificationException foreach语法并发修改失败
        values = new ArrayList<>(List.of(2, 3, 5));
        for (Integer integer : values) {
            values.remove(integer);
        }
    }

}
