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

import java.util.function.Consumer;

/**
 * @program: jguid
 * @description: {@link Consumer} Consumer 模式下的使用
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-15
 */
public class ConsumerInfo {

    public static void main(String[] args) {
        //JDK 实现
        final Consumer<Object> consumer = System.out::println;
        consumer.accept("fufeng");

        //自定义 消费
        final Consumer<Object> consumerEcho = ConsumerInfo::echo;
        consumerEcho.accept("magicLuo");

        // Fluent API 链式编程
        // consumerEcho -> consumer -> consumer
        consumerEcho.andThen(consumer).andThen(consumer).accept("magic fufeng");

        print(ConsumerInfo::echo,"print hello");
    }

    private static void print(Consumer<? super CharSequence> consumer,String message){
        consumer.accept(message);
    }

    private static void echo(Object object){
        System.out.println("echo : "+object);
    }
}
