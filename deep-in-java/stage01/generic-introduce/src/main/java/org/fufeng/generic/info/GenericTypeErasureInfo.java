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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: jguid
 * @description: {@link Type} 泛型简单示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-15
 */
public class GenericTypeErasureInfo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("");
        // 如果不存在泛型，那里这里就会变成 String str = (String)list.get(0);
        final String str = list.get(0);

        TypeA<String> typeA = new TypeA<>();
        typeA.equals(typeA);

        TypeC typeC = new TypeC();
        typeC.compareTo(typeC);
    }

    // 这里的泛型作用在本身约束上
    public static class TypeA<T>{

    }

    // 这里的泛型作用在方法定义上
    public static class TypeC implements Comparable<TypeC>{
        @Override
        public int compareTo(TypeC typeC) {
            return 0;
        }
    }
}
