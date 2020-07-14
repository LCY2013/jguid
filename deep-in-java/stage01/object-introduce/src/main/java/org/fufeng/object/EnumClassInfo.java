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
package org.fufeng.object;

import java.lang.reflect.Modifier;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: 枚举在java语言中的使用
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-14
 */
public class EnumClassInfo {

    public static void main(String[] args) {
        // THREE 位置应该在那里？
        print(Counting.ONE);
        print(Counting.FIVE);

        System.out.println("----------------");
        // 枚举是如何输出成员变量的？
        print(EnumCounting.ONE);
        print(EnumCounting.FIVE);
        printMetaData(EnumCounting.ONE);
        printMetaData(EnumCounting.FIVE);

        System.out.println("----------------");
        // 自定义枚举实现
        printCountingMembers();

        System.out.println("----------------");
        // JDK枚举提升
        printEnumCountingMembers();
    }

    static void print(Counting counting){
        System.out.println(counting);
    }

    static void print(EnumCounting enumCounting){
        System.out.println(enumCounting);
    }

    static void printMetaData(Enum enumeration){
        // 说明任何枚举都扩展了 java.lang.Enum
        System.out.println("Enumeration type : " + enumeration.getClass());
        // Enum#name()  = 成员名称
        // Enum#ordinal() = 成员定义位置
        System.out.println("member : " + enumeration.name());
        System.out.println("ordinal : " + enumeration.ordinal());
        // values() 方法是 Java 编译器给枚举提升的方式
    }

    static void printCountingMembers(){
        Stream.of(Counting.values())
                .forEach(System.out::println);
    }

    static void printEnumCountingMembers(){
        Stream.of(EnumCounting.values())
                .forEach(System.out::println);
    }

    enum EnumCounting implements Cloneable{
        ONE(1){
            @Override
            public String valueAsString() {
                return String.valueOf(this.getValue());
            }
        },
        TWO(2){
            @Override
            public String valueAsString() {
                return String.valueOf(this.getValue());
            }
        },
        THREE(3){
            @Override
            public String valueAsString() {
                return String.valueOf(this.getValue());
            }
        },
        FOUR(4){
            @Override
            public String valueAsString() {
                return String.valueOf(this.getValue());
            }
        },
        FIVE(5){
            @Override
            public String valueAsString() {
                return String.valueOf(this.getValue());
            }
        };
        private int value;

        EnumCounting(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String valueAsString(){
            return String.valueOf(this.value);
        }

        @Override
        public String toString() {
            return "EnumCounting{" +
                    "value=" + value +
                    '}';
        }

    }

    /**
     *  扩展计数
     */
    class ExtendedCounting extends Counting{

        public ExtendedCounting(int value) {
            super(value);
        }

        @Override
        String valueAsString() {
            return toString();
        }
    }

    /**
     *  枚举类: 计数
     *  强制类型约束(相当于常量)
     */
    abstract static class Counting extends CloneableInfo.Data implements Cloneable{
        private int value;

        public Counting(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Counting{" +
                    "value=" + value +
                    '}';
        }

        abstract String valueAsString();

        public static final Counting ONE = new Counting(1){
            @Override
            String valueAsString() {
                return String.valueOf(this.getValue());
            }
        };

        public static final Counting TWO = new Counting(2){
            @Override
            String valueAsString() {
                return String.valueOf(this.getValue());
            }
        };

        public static final Counting THREE = new Counting(3){
            @Override
            String valueAsString() {
                return String.valueOf(this.getValue());
            }
        };

        public static final Counting FOUR = new Counting(4){
            @Override
            String valueAsString() {
                return String.valueOf(this.getValue());
            }
        };

        public static final Counting FIVE = new Counting(5){
            @Override
            String valueAsString() {
                return String.valueOf(this.getValue());
            }
        };

        public static Counting[] values(){
            //Fields -> filer -> public static final field -> get
            return Stream.of(Counting.class.getDeclaredFields())
                    .filter(field -> {
                        final int modifiers = field.getModifiers();
                        return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)
                                && Modifier.isFinal(modifiers);
                    }).map(field -> {
                        //Field -> Counting
                        try {
                            return (Counting)field.get(null);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }).toArray(Counting[]::new);//.collect(Collectors.toList()).toArray(new Counting[0]);
        }
    }
}
