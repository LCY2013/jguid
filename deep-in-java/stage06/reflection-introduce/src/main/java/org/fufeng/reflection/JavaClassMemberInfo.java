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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * @program: jguid
 * @description: {@link Class} java类对象成员
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-29
 * @see Class
 */
public class JavaClassMemberInfo {

    private int value;

    public static void main(String[] args) {
        /*
           java 类成员对象来自于 java.lang.Class
           成员访问性
           所有成员 getDeclaredXXX() ->
                getDeclaredFields()
                getDeclaredConstructors()
                getDeclaredMethods()
         */

        // getDeclaredFields() 获取当前类所声明的所有字段(包含public、private)
        // getFields() 获取当前类所声明的共有字段(public)
        final List<Field> allDeclaredFields = getAllDeclaredFields(ExtendedData.class);
        final Field[] declaredFields = ExtendedData.class.getDeclaredFields();
        final Field[] fields = ExtendedData.class.getFields();
        System.out.println("ExtendedData 类所有层次性声明字段:"+allDeclaredFields.stream().map(Field::getName).collect(Collectors.joining(",")));
        System.out.println("ExtendedData 类所有声明字段:"+ Stream.of(declaredFields).map(Field::getName).collect(Collectors.joining(",")));
        System.out.println("ExtendedData 类public声明字段:"+Stream.of(fields).map(Field::getName).collect(Collectors.joining(",")));
    }

    static List<Field> getAllDeclaredFields(Class<?> clazz){
        if (Object.class.equals(clazz)){
            return Collections.emptyList();
        }
        final List<Field> allDeclaredField = new LinkedList<>();
        final Field[] declaredFields = clazz.getDeclaredFields();
        allDeclaredField.addAll(asList(declaredFields));
        // 获取父字节码
        Class<?> superclass = clazz.getSuperclass();
        while (true){
            if (superclass == null || Object.class.equals(superclass)){
                break;
            }
            allDeclaredField.addAll(getAllDeclaredFields(superclass));
            superclass = superclass.getSuperclass();
        }
        return allDeclaredField;
    }

    public JavaClassMemberInfo() {
    }

    class Data{
        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    class ExtendedData extends Data{
        private String name;
        public int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
