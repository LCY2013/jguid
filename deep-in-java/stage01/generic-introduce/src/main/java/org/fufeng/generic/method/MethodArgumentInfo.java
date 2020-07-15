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
import java.util.Collection;

/**
 * @program: jguid
 * @description: {@link Method} 方法参数信息示例
 *   Effective Java 建议不要超过四个参数
 *   Java 8 Lambda 告诉用户，最多使用三个
 *   Runnable(Action) 零个
 *   Consumer 一个
 *   Function BiConsumer 两个
 *   BiFunction 三个
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-15
 */
public class MethodArgumentInfo implements Comparable<MethodArgumentInfo>{

    public static void main(String[] args) {
        // argument 通常是指方法形参
        // parameter 通常是系统交互定义的参数信息
    }

    // 定义一个成员变量
    private int value;

    // 动词 执行词
    @Override
    public int compareTo(MethodArgumentInfo o) {
        return 0;
    }

    // 方法参数名称设计
    public boolean equals(Object obj) {
        return this.value == ((MethodArgumentInfo)obj).value;
    }

    // 单向传递 - 参数类型对等
    public void copy(Object source,Object target){

    }

    // 单向传递 - 参数类型不对等
    public void add(Collection<Object> collection,Object item){

    }

    /**
     *  多项传递 - 参数类型不对等
     * @param collection 集合
     * @param items 集合待添加元素信息
     */
    public void add(Collection<Object> collection,Object... items){

    }

    /**
     *  多项传递 - 参数类型不对等
     * @param collection 集合
     * @param itemName one element
     * @param items much element
     */
    public void add(Collection<Object> collection,Object itemName,Object... items){

    }



}
