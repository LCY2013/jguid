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
package org.fufeng.generic.method;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @program: jguid
 * @description: {@link Method} 方法构建信息建议
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-15
 */
public class MethodReturnTypeInfo {

    // 需要返回快照尽可能的使用ArrayList
    public static void main(String[] args) {
        final List<Integer> errorNumbers = getErrorNumbers();
        printCollection(errorNumbers);
        // 抛出 UnsupportedOperationException 异常
        // 因为 Arrays.asList() , 原因是这里java.util.AbstractList.add(int, E)
        // Arrays.asList() 它表现上是一个只读，里面的实现没有限制读，而是它父类抽象的实现
        //errorNumbers.add(8);

        errorNumbers.set(0,0);
        printCollection(errorNumbers);

        final List<Integer> unmodifiableList = getUnmodifiableList();
        printCollection(unmodifiableList);
        // 抛出 UnsupportedOperationException
        //unmodifiableList.set(0,0);
        //printCollection(unmodifiableList);

        final Integer[] numberArray = getNumberArray();
        printArray(numberArray);
        numberArray[0] = 0;
        printArray(numberArray);
    }

    public static void printCollection(Collection<?> collection){
        collection.stream().map(item -> item + " ").forEach(System.out::print);
        System.out.println();
    }

    public static void printArray(Integer[] integers){
        for (Integer integer : integers){
            System.out.print(integer+" ");
        }
        System.out.println();
    }

    // 面向对象 -> 封装、继承、多态
    // 建议: 返回类型需要抽象(强类型)，除Object
    // 抽象返回类型的意义，调用方容易处理
    // 越具体的越难通用

    // 这里需要返回一个有序，去重的集合信息
    public TreeSet<String> getValue(){ // 固定返回信息，不通用
        return new TreeSet<>();
    }

    // 动词 形容词  名词
    public SortedSet<String> getSortedValue(){ // 返回类型向上，增大通用性
        return new TreeSet<>();
    }

    // 如果返回类型是集合类型 尽量返回Collection,而不是List、Set这种
    // 如果不存在写操作 尽可能返回Iterator

    // 建议: 尽可能使用集合框架的接口，避免使用数组
    // 1、 collection 相较于 array ， 有更多的操作 ， 比如: toArray()
    // 2、 collection 相较于 array ， 有更多的工具，可以返回只读类型，数组只能保证长度但是不能保证不可变
    public static List<Integer> getErrorNumbers(){
        return Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    }

    // array 只能保证长度不变，但是不能够保证数据的不可变
    public static Integer[] getNumberArray(){
        return new Integer[]{1,2,3,4,5,6,7};
    }

    // 建议: Collection 可以保证数据的不可变
    public static List<Integer> getUnmodifiableList(){
        return Collections.unmodifiableList(Arrays.asList(1,2,3,4,5,6,7));
    }

    // 建议: 如果存在数据集合非可读，那么可以返回快照
    public static List<Integer> getSnapshotList(){
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        // 返回数据集合的快照
        return new ArrayList<>(numbers);
    }
}
