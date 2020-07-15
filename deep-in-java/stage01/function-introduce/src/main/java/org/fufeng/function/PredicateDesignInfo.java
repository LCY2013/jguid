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
package org.fufeng.function;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: {@link  Predicate} Predicate 模式
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-15
 */
public class PredicateDesignInfo {

    public static void main(String[] args) {
        // 新建一个集合
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        Predicate<Integer> predicateNotEven = integer -> integer % 2 == 0;
        Predicate<Integer> predicateNotOdd = integer -> integer % 2 != 0;

        filter(integers,predicateNotEven).stream().map(integer -> integer + " ").forEach(System.out::print);
        System.out.println();
        filter(integers,predicateNotOdd).stream().map(integer -> integer + " ").forEach(System.out::print);
        System.out.println();

        // 等价于
        Stream.of(1,2,3,4,5,6,7,8)
                .filter(integer -> integer % 2 == 0)
                .map(integer -> integer + " ")
                .forEach(System.out::print);
        System.out.println();
        Stream.of(9,10,11,12,13,14,15)
                .filter(integer -> integer % 2 != 0)
                .map(integer -> integer + " ")
                .forEach(System.out::print);
    }

    // 按指定计算量返回一个新的集合
    private static <E> Collection<E> filter(Collection<E> collection,
                                            Predicate<E> predicate) {
        // copy 老集合
        List<E> copy = new ArrayList<>(collection);
        /*final Iterator<E> iterator = copy.iterator();
        while (iterator.hasNext()){
            E element = iterator.next();
            if(predicate.test(element)){
                iterator.remove();
            }
        }*/
        copy.removeIf(predicate);
        return Collections.unmodifiableCollection(copy);
    }

}
