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

import org.springframework.core.ResolvableType;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: {@link ResolvableType} Spring 泛型处理工具
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-15
 */
public class ResolvableTypeInfo<T extends Serializable> {

    private Map<String,List<Map<String,Object>>> value;

    public static void main(String[] args) throws NoSuchFieldException {
        resolved();
    }

    private static void resolved() throws NoSuchFieldException {
        // 获取成员变量字段信息
        Field fieldValue = ResolvableTypeInfo.class.getDeclaredField("value");
        // 通过Spring工具获取转换工具
        final ResolvableType resolvableType = ResolvableType.forField(fieldValue);
        //System.out.println(resolvableType.getGeneric(0));
        //System.out.println(resolvableType.getGeneric(1));
        Stream.of(resolvableType.getGenerics()).forEach(System.out::println);

        // 获取该类的泛型参数信息
        TypeVariable[] typeParameters
                = ResolvableTypeInfo.class.getTypeParameters();
        for (TypeVariable tv : typeParameters){
            System.out.println(tv);
        }

        // 泛型具体化的参数信息获取
        ResolvableTypeInfo<String> resolvableTypeInfo = new ResolvableTypeInfo<>();
        typeParameters = resolvableTypeInfo.getClass().getTypeParameters();
        Stream.of(typeParameters).forEach(System.out::println);
    }

}
