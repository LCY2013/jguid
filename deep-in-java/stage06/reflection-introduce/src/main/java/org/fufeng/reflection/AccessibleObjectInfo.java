/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-29
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: jguid
 * @description: {@link AccessibleObject} 对象可见性分析
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-29
 * @see AccessibleObject
 */
public class AccessibleObjectInfo {

    public static void main(String[] args) throws NoSuchMethodException {
        /*
            AccessibleObject 是Constructor、Field、Method、Executable的父类
            访问性检查 isAccessible()
            修改可访问性 setAccessible()
         */
        String str = "hello,fufeng!";
        execute(()->str.toString());

        Method toStringMethod = String.class.getMethod("toString");

        // 开始不设置字段访问可见性
        execute(()->{
            try {
                // 默认情况下任务AccessibleObject对象都会做访问性检查
                toStringMethod.invoke(str);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        // 开始不设置字段访问可见性
        execute(()->{
            try {
                // 默认情况下任务AccessibleObject对象都会做访问性检查
                toStringMethod.setAccessible(true);
                toStringMethod.invoke(str);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        // 开始不设置字段访问可见性
        execute(()->{
            try {
                // 默认情况下任务AccessibleObject对象都会做访问性检查
                // 关闭访问性检查，提升执行性能
                toStringMethod.setAccessible(false);
                toStringMethod.invoke(str);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        // 反射调用
    }

    /**
     *  定义一个执行函数
     * @param task 任务
     */
    private static void execute(Task task){
        // 定义一个执行次数
        int times = 10000;
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        for (int i=0;i<times;i++){
            task.exe();
        }

        // 记录结束时间
        long endTime = System.currentTimeMillis();

        System.out.printf("执行 %d 次, 耗时 %d ms\n",times,(endTime-startTime));
    }
}
