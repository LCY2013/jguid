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

import java.lang.reflect.Field;

/**
 * @program: jguid
 * @description: {@link String} java String 类型的示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-14
 */
public class StringInfo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 基本类型都是常量化的
        int i = 1;
        // String 类型的变量是存放在常量池中，有利于重复利用，是jvm对文本类型的优化
        String s1 = "fufeng";
        //C 写法 char* var = malloc(length(char) * 5);
        //var ="Hello";

        //面向对象规则: 一切对象需要new
        //合理的写法
        String s2 = new String("fufeng");

        System.out.println("s1 == s2 ? "+(s1 == s2));
        System.out.println("s1 equals s2 ? "+(s1.equals(s2)));

        // 从1.5开始对象属性可以通过反射修改
        char[] chars = "magic".toCharArray();
        // 获取String类型中的Value值
        final Field value = String.class.getDeclaredField("value");
        // 设置成外部可以访问
        value.setAccessible(true);
        // 将 chars 的值设置到 s1中
        value.set(s1,chars);

        System.out.println(s1);

        ExtendableString extendableString = new ExtendableString("magic");
        System.out.println(extendableString);
    }

    /**
     *  模拟扩展String类型
     */
    public static class ExtendableString{
        private final char[] value;

        /** Cache the hash code for the string */
        private int hash; // Default to 0

        public ExtendableString(char[] value) {
            this.value = value;
        }

        public ExtendableString(String value) {
            this.value = value.toCharArray();
        }

        public final int hashCode() {
            int h = hash;
            if (h == 0 && value.length > 0) {
                char val[] = value;

                for (int i = 0; i < value.length; i++) {
                    h = 31 * h + val[i];
                }
                hash = h;
            }
            return h;
        }

        public boolean equals(Object anObject) {
            if (this == anObject) {
                return true;
            }
            if (anObject instanceof ExtendableString) {
                ExtendableString anotherString = (ExtendableString)anObject;
                int n = value.length;
                if (n == anotherString.value.length) {
                    char v1[] = value;
                    char v2[] = anotherString.value;
                    int i = 0;
                    while (n-- != 0) {
                        if (v1[i] != v2[i])
                            return false;
                        i++;
                    }
                    return true;
                }
            }
            return false;
        }
    }

}
