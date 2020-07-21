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
package org.fufeng.collection.basic;

import java.util.*;

/**
 * @program: jguid
 * @description: {@link Collection} 几乎所有老版集合实现都是线程安全
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-20
 */
public class LegacyCollectionInfo {

    public static void main(String[] args) {
        // Vector 与 List
        VectorVsList();
        // Vector 与 Stack
        VectorVsStack();
        // HashTable 与 HashMap
        HashTableVsHashMap();
        // Enumeration 与 Enum
        enumerationVsEnum();
        // bitSet
        bitSet();
    }

    /**
     *  位运算
     */
    private static void bitSet() {
        // 位操作集合，与ByteBuffer结合使用
    }

    /**
     *  enumeration 与 Enum
     */
    private static void enumerationVsEnum() {
        // Enumeration 主要用于早期迭代,从jdk1.0开始，从java.util.Iterator 1.2 开始引入
        String value = "1,2,3,4,5,6,7";
        StringTokenizer st = new StringTokenizer(value,",");
        Enumeration enumeration = st;
        // 下面的例子等价于 iterator.hasNext()
        while (enumeration.hasMoreElements()){
            // 下面的例子等价于 iterator.next()
            final String valueOf = String.valueOf(enumeration.nextElement());
            System.out.println(valueOf);
        }
        // Iterable 从jdk1.5开始引入,用于增强forEach操作
    }

    /**
     *  HashTable 与 HashMap
     */
    private static void HashTableVsHashMap() {
        // HashTable 继承Dictionary(字典),实现Map接口
        // 存在Synchronized 修饰方法
        // 首先value不能为null，其次key存在一个key.hash()计算，所以key也不能为null

        // HashMap 继承至AbstractMap，实现Map接口
        // 方法不存在Synchronized修饰
        // key 和 value都可以存在null,由于对应的key为null时，只会存在一个同样的hash，可以只能存在一个key为null的键

        // HashMap存在一个线程安全的实现ConcurrentHashMap
        // key 和 value都不允许存在null值
        // 它的具体实现是 <= 1.7 分段锁，头插法实现数据的新增
        // >= 1.8 保留分段锁设计，实际是在每个桶做插入的时候做Synchronized 和 CAS或者无锁操作，以及改进的尾插法
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(null,null);
    }

    /**
     *  Vector 与 Stack 比较
     */
    private static void VectorVsStack() {
        // Vector FIFO
        // Stack LIFO 继承自 Vector
    }

    /**
     *  Vector 于 List 比较
     */
    private static void VectorVsList() {
        // Vector 是早期的集合Java集合实现 从1.0开始，方法都加上了Synchronized
        // List 后期的ArrayList 每个方法都没有加上同步锁
        // 在jdk8以后的版本中，没有线程竞争的Synchronized也是被优化掉没有锁
        // 他们的底层存储都是一个数组对象，但是他们的扩容机制存在差异
        // Vector 扩容如果不指定就默认是两倍
        // ArrayList 扩容是当前容量的1.5倍
        Vector<String> vector = new Vector<>();
        ArrayList<String> list = new ArrayList<>();
        vector.add("vector");
        list.add("list");
    }

}
