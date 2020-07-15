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
package org.fufeng.generic.info;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @program: jguid
 * @description: {@link Type} {@link Class} 类型转换
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-15
 */
public class ClassCastInfo {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add("2");

        // Class -> Type
        Type type = int.class;
        Class intClass = int.class;

        List<Object> listObj = list;

        List<String> strList = Collections.emptyList();
        List<String> stringList = Arrays.asList("A");
        // Diamond 语法
        List<String> DiamondList = new ArrayList<>();

        // java 9 语法 局部变量揣测
        var listVar = new ArrayList<>();

        exchange(list,stringList);
    }

    /**
     *  集合类型交换
     * @param otherList 其中一个集合
     * @param antherList 另一个集合
     */
    private static void exchange(List otherList,List antherList){
        otherList.addAll(antherList);
        System.out.println((otherList.get(0)));
        System.out.println(otherList);
    }

}
