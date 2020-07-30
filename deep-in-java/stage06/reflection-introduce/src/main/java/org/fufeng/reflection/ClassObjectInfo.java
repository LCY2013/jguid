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

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.AbstractList;

/**
 * @program: jguid
 * @description: {@link Class} 和 {@link Object} 类对象
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-29
 * @see Class
 * @see Object
 */
public class ClassObjectInfo {

    public static void main(String[] args) {
        // Class 对象
        // 具体类对象
        // 没有类方法isConcrete()去判断是否是具体类
        System.out.println(Modifier.isAbstract(Object.class.getModifiers()));
        // 抽象类对象
        System.out.println(Modifier.isAbstract(AbstractList.class.getModifiers()));
        // 接口类对象
        System.out.println(Serializable.class.isInterface());
        // 枚举类对象
        System.out.println(Color.class.isEnum());
        // 注解类对象
        System.out.println(ConstructorProperties.class.isAnnotation());
        // 原生类对象
        System.out.println(int.class.isPrimitive());
        System.out.println(long.class.isPrimitive());
        // 数组类型
        System.out.println(int[].class.isArray());
        System.out.println(String[].class.isArray());
        // 特殊类型
        System.out.println(void.class);
        
    }

}
