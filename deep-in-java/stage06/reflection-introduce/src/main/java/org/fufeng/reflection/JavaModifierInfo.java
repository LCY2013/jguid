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

import java.lang.reflect.Modifier;

/**
 * @program: jguid
 * @description: {@link Modifier} 类访问类型计算量
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-29
 * @see Modifier
 */
public strictfp class JavaModifierInfo {

    private String data;

    private static final String NAME = "JavaModifierInfo";

    private static volatile Object object = new Object();

    private transient int hashCode; // 缓存 hash code，transient 属性不被序列化

    private static transient int a; // static 字段就是 transient

    public static void main(String[] args) {
        JavaModifierInfo javaModifierInfo = new JavaModifierInfo();
        float sum = 0;
        for (int i=0;i<10000;i++) {
            sum = javaModifierInfo.add(sum,1.1f);
        }
        System.out.println(sum);
    }

    protected JavaModifierInfo() {
    }

    static final synchronized <T> T[] of(T... values){
        return values;
    }

    static native void copy(Object[] array);

    strictfp float add(float one, float two) {
        return one + two;
    }

    // strictfp 精准浮点，在这里个关键字修饰的范围内，所有浮点计算都是精准的
    strictfp interface Data {

        String getData();

    }

}
