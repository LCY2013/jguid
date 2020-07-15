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

import java.util.function.Function;

/**
 * @program: jguid
 * @description: {@link Function} 自定义函数接口使用
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-15
 */
public class FunctionInterfaceInfo {

    public static void main(String[] args) {
        FunctionInterface functionInterface = () ->
                System.out.println("fufeng");
        functionInterface.execute();
        FunctionInterfaceWithoutAnnotation interfaceWithoutAnnotation = () ->
                System.out.println("magic");
        interfaceWithoutAnnotation.execute();
    }

    @FunctionalInterface
    public interface FunctionInterface {

        void execute();

        // 不能同时出现两个一样定义的方法
        //void exe();

        default String getDescription() {
            return String.valueOf(this);
        }
    }

    //@FunctionalInterface 没有标注函数式声明注解，该注解只能描述接口
    public interface FunctionInterfaceWithoutAnnotation {
        void execute();
    }
}
