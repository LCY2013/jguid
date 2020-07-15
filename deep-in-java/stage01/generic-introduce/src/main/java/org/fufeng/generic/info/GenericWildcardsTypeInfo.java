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
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @program: jguid
 * @description: {@link Type} 泛型使用
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-15
 */
public class GenericWildcardsTypeInfo {

    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();

        upperBoundedWildcards(numbers);
        unboundedWildcards(numbers);
    }

    private static void upperBoundedWildcards(List<Number> numbers){
        // 泛型上界通配符示例
        // Number -> (Integer , Double , Long , Float , Byte , Short)
        numbers.add(Short.valueOf("7"));
        numbers.add(Integer.valueOf(7));
        numbers.add(Float.valueOf(7));
        numbers.add(Long.valueOf(7));

        List<Byte> bytes = Arrays.asList(Byte.valueOf("7"));
        List<Short> shorts = Arrays.asList(Short.valueOf("7"));
        List<Integer> integers = Arrays.asList(1,2,3);

        numbers.addAll(bytes);  // <? extends Number>
        numbers.addAll(shorts); // <? extends Number>
        numbers.addAll(integers); // <? extends Number>

        // 上面numbers集合操作的对象类型最后都会统一转换成Number
        // 上面numbers集合待操作的对象类型可以是具体的一些子类型

        forEach(numbers,System.out::print);
    }

    private static void unboundedWildcards(List<Number> numbers){
        // 完全通配类型
        // 在运行时与非统配泛型会出现方法签名冲突
        // 然后完全通配类型，可以适配任意类型，比如集合
        // 反而具体类型泛型会限制类型范围
        forEach(numbers);
    }

    private static void lowerBoundedWildcards(List<Number> numbers){
        lowerBounded(numbers,numbers);
    }

    private static void lowerBounded(List<? extends Number> producer,List<? super Number> consumer){
        // PECS stands for producer-extends, consumer-super.
        // 读取数据（生产者）使用 extends
        for (Number number : producer) {

        }
        // 操作输出（消费者）使用 super
        consumer.add(1);
        consumer.add((short) 2);
    }

    // 这里?代表任意类型和Object效果一致，在泛型擦除后就可下面的方法一样，所以这两个方法不能同时存在
    private static void forEach(Iterable<?> source){
        for (Object obj : source){
            System.out.print(obj+" ");
        }
        System.out.println();
    }

//    private static void forEach(Iterable<Object> source){
//        for (Object obj : source){
//            System.out.print(obj+" ");
//        }
//        System.out.println();
//    }

    private static void forEach(Iterable<?> source, Consumer<Object> consumer){
        /*for (Object obj : source){
            consumer.accept(obj);
        }*/
        source.forEach(consumer);
        System.out.println();
    }

    private static void forEach(Collection<?> source,Consumer<Object> consumer){
        source.stream().map(item -> item+" ").forEach(consumer);
        System.out.println();
    }

}
