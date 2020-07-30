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
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @program: jguid
 * @description: {@link Type}
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-29
 * @see Type
 */
public class JavaTypeInfo {

    @ConstructorProperties("v")
    public JavaTypeInfo(String v) {
    }

    public static void main(String[] args) {
        final List<Integer> values = asList(1, 2, 3);
        System.out.println(values.getClass().toGenericString());
        System.out.println(StringList.class.getGenericSuperclass().toString());

        Color.values(); // JVM 虚拟机生成的方法
    }

    class StringList extends ArrayList<String>{

    }

//    class Numbers extends Enum<Numbers>{
//
//        /**
//         * Sole constructor.  Programmers cannot invoke this constructor.
//         * It is for use by code emitted by the compiler in response to
//         * enum type declarations.
//         *
//         * @param name    - The name of this enum constant, which is the identifier
//         *                used to declare it.
//         * @param ordinal - The ordinal of this enumeration constant (its position
//         *                in the enum declaration, where the initial constant is assigned
//         */
//        protected Numbers(String name, int ordinal) {
//            super(name, ordinal);
//        }
//    }

    interface One extends Annotation{

    }

}
