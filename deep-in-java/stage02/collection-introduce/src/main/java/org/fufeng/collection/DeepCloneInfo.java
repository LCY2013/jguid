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

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: jguid
 * @description: {@link Cloneable} 克隆标示接口
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-20
 */
public class DeepCloneInfo {

    public static void main(String[] args) {
        // 测试集合clone
        ArrayList<String> stringArrayList =
                new ArrayList<>();
        stringArrayList.add("A");
        stringArrayList.add("B");
        stringArrayList.add("C");
        // 浅克隆
        System.out.println("浅 List clone ...");
        final ArrayList<String> cloneStringArrayList = (ArrayList<String>) stringArrayList.clone();
        displayDiff(stringArrayList,cloneStringArrayList);

        // 深度克隆
        System.out.println("深 List clone ...");
        final List<String> deepCloneStringList = deepClone(stringArrayList);
        displayDiff(stringArrayList,deepCloneStringList);

        // 深度克隆通过 序列化
        System.out.println("序列化 List Clone");
        final List<String> serializationStringList = deepSerialization(stringArrayList);
        displayDiff(stringArrayList,serializationStringList);
    }

    /**
     *  通过序列化深度克隆集合对象
     * @param stringArrayList 待集合对象
     * @return 序列化克隆后的集合数据
     */
    private static List<String> deepSerialization(List<String> stringArrayList){
        // 先通过复制一份，防止集合添加
        ArrayList<String> copy = new ArrayList<>(stringArrayList);

        // 创建序列化输出流对象
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
            // 先将对象序列化到输出流中
            objectOutputStream.writeObject(copy);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建序列化输入流
        ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(outputStream.toByteArray());

        // 创建结果返回集合
        List<String> clone = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            clone = (List<String>) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return clone;
    }

    /**
     *  比较两个集合元素是否相等
     * @param values 原集合
     * @param clone 克隆后的集合
     */
    private static void displayDiff(List<String> values, List<String> clone) {
        // 通过下标打印所有的内部数据
        for (int i = 0; i < values.size(); i++) {
            System.out.printf("values.get(%d).equals(clone.get(%d)) ? %s\n",
                    i,i,values.get(i).equals(clone.get(i)));
            System.out.printf("values.get(%d) == clone.get(%d) ? %s\n\n",
                    i,i,values.get(i) == clone.get(i));
        }
    }

    /**
     *  深度克隆
     * @param value 需要深度克隆的数据
     * @return 返回一个深度克隆的集合
     */
    private static List<String> deepClone(List<String> value){
        ArrayList<String> deepClone = new ArrayList<>();
        value.forEach(str -> deepClone.add(new String(str)));
        return deepClone;
    }

}
