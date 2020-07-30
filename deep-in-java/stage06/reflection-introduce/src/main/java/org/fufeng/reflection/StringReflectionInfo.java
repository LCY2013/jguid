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

import java.lang.reflect.Field;

/**
 * @program: jguid
 * @description: Reflect 反射应用
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-29
 */
public class StringReflectionInfo {

    /**
     *  java5开始反射可以修改对象属性，即使它被final修饰
     * @param args 命令行参数
     */
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 定义一个字符串对象
        String editContext = "fufeng";
        String updateContext = "magic";
        // 修改前输出
        System.out.println("反射前的 editContext : "+editContext);
        // 获取String 类的成员变量 value
        // @Stable
        // private final byte[] value;
        final Field value = String.class.getDeclaredField("value");
        // 设置value属性访问可见性,关闭访问性检查
        value.setAccessible(true);
        // 替换editContext文本中的内容
        value.set(editContext,updateContext.getBytes());
        // 输出替换后的值
        System.out.println("反射后的 editContext : "+editContext);
    }

}
