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
import java.util.Collections;
import java.util.List;

/**
 * @program: jguid
 * @description: {@link Collection} 集合类型检查
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-20
 */
public class CheckedTypeCollectionInfo {

    public static void main(String[] args) {
        // List 元素类型是 java.lang.Integer
        List<Integer> values = new ArrayList<>(List.of(1,2,3,4,5,6,7));
        //values.add("1");  // 类型检查，编译错误
        // 泛型是编译是检查，运行时擦除

        // 引用List<Integer> 类型对象 values
        List referencedValues = values;

        System.out.println(values == referencedValues); // true

        // 这个时候向集合中添加其他类型数据,这里可以绕过编译检查
        // 这里之所以可以成功是由于泛型在后面都会变成Object List <==> List<Object>
        // 所以这里可以添加成功
        referencedValues.add("fufeng");
        // 打印数据
        // values.forEach(System.out::println); 这里存在类型转换所以会出问题
        for (Object obj : values){
            System.out.println(obj);
        }

        // values
        // [0] = 1, [1] = 2, [2] = 3, [3] = "A"
        // 创建时尚未检查内部的数据是否类型相同，操作时做检查，Wrapper 模式（装饰器模式）的运用
        // Collections.checked* 接口是弥补泛型集合在运行时中的擦写中的不足
        // 强约束：编译时利用 Java 泛型、运行时利用  Collections.checked* 接口
        List<Integer> checkedTypeValues = Collections.checkedList(values, Integer.class);
        //checkedTypeValues.add("1"); // 编译错误
        // 运行时检查

        // 又将 checkedTypeValues 引用给 referencedValues
        referencedValues = checkedTypeValues;

        System.out.println(referencedValues == values);

        System.out.println(referencedValues == checkedTypeValues);

        // 添加 "B" 进入 referencedValues,这里会做类型检查
        referencedValues.add("B");
    }

}
