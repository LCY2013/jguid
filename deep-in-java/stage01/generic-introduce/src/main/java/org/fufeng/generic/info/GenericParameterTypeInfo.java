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

import java.io.Serializable;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * @program: jguid
 * @description: {@link Parameter} 泛型参数类型相关信息
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-15
 */
public class GenericParameterTypeInfo {

    public static void main(String[] args) {
        // 正确测试用例
        Container<String> container = new Container<>("fufeng");

        // 错误测试用例 Integer 不是CharSequence的具体实现
        //Container<Integer> integerContainer = new Container<Integer>(7);

        // 测试StringBuffer 与 StringBuilder
        // 结论: StringBuffer 与 StringBuilder 都是CharSequence的子类实现，所以不存在问题
        // 泛型在运行时是会泛型擦除
        Container<StringBuffer> sbContainer = new Container("fufeng");
        Container<StringBuilder> sbrContainer = new Container("fufeng");
        // 通过构造器传入的String参数,这里后面的泛型约束并没有使用StringBuffer，所以上面没有问题,泛型在运行时都是Object
        System.out.println(sbContainer.getElement());

        // 这里不能够使用普通的String，因为前面的定义约束了泛型类型
        sbContainer.setElement(new StringBuffer("magicLuo"));
        System.out.println(sbContainer.getElement());

        add(new ArrayList<>(),1);

        add(new ArrayList<>(),"fufeng");

        add(new TreeSet<>(),"magic");

        forEach(Arrays.asList(1,2,3,4,5,6,7),System.out::println);
    }

    // 限制E Super是来自于 CharSequence
    public static class Container<E extends CharSequence>{

        private E element;

        public Container(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }
    }

    // 多界限泛型类型(无界限)
    // 学生
    public static class Student{

    }

    // 人
    public static interface Person{

    }

    // 知识人
    public static interface Intellectual{

    }

    // 多界限泛型类型extend 第一个参数可以是类也可以是接口
    // 第二个或者更多的接口就必须使用接口
    public static class Temple<T extends Serializable & Person & Intellectual>{

    }

    public static class TempleOne extends Student
            implements Serializable,Person,Intellectual{

    }

    // 将一个类型 添加到某个类型的集合中
    private static <C extends Collection<E>,E extends Serializable>
            void add(C collection,E element){
        collection.add(element);
    }

    // 通过传入一个实现Iterable接口的具体类和一个消费式函数接口
    private static <C extends Iterable<E>,E extends Serializable>
            void forEach(C source, Consumer<E> consumer){
        /*for (E e : source) {
            consumer.accept(e);
        }*/
        source.forEach(consumer);
    }

}
