/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-14
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.module.java.info;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: 语法表达式
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-14
 */
public class LambdaMain {

    // lambda 表达式几种 模式
    // SCFP = Supplier + Consumer + Function + Predicate
    // 不包括 Action模式

    // Action 模式
    private static void showAction(){
        // 普通模式
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        // 方式一
        Runnable runnableAction = () -> {};
        // 方式二
        Runnable runnableAc = LambdaMain::stream;
        runnableAc.run();
    }

    // Supplier
    private static void showSupplier(){
        //定义普通字符类型
        String str = "fufeng";

        //示例一
        Supplier<String> stringSupplier = () -> "fufeng";
        //示例二
        Supplier<String> stringSup = () -> Integer.valueOf(7).toString();
        println(stringSup.get());
    }

    // Consumer
    private static void showConsumer() {
        // 传统方式
        PropertyChangeListener pcl = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

            }
        };

        //方式一
        PropertyChangeListener pclNew = (event) -> {};
        //方式二
        // 简略写 ， 等价于 java.beans.PropertyChangeListener.propertyChange
        // 属于入参，没有返回值
        PropertyChangeListener pclNewTwo = LambdaMain::println;
        pclNewTwo.propertyChange(new PropertyChangeEvent(new Object()
                ,"key","2","3"));

    }

    // Function
    private static void showFunction(){
        final Function<String, Integer> comparator = LambdaMain::comparator;
        println(comparator.apply("magicLu"));
    }

    // predicate
    private static void showPredicate() {

    }

    public static void main(String[] args) {
        Action action = () -> {};
        //stream();
        println("--------------show action");
        showAction();
        println("--------------show supplier");
        showSupplier();
        println("--------------show consumer");
        showConsumer();
        println("--------------show predicate");
        showPredicate();
        println("--------------show function");
        showFunction();
    }

    //声明函数式接口
    @FunctionalInterface
    public interface Action{
        void execute();
        default void doExecute(){
            execute();
        }
    }

    private static Integer comparator(String value){
        return value.compareToIgnoreCase("fufeng");
    }

    private static void println(Object object){
        System.out.println(object);
    }

    private static void stream(){
        Stream.of(8,6,0,9,8,1,2,3,4,5)
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                //.forEach(System.out::println);
                .forEach(LambdaMain::println);
    }

}
