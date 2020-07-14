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

/**
 * @program: jguid
 * @description: 测试 {@link Integer} 使用细节
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-14
 */
public class IntegerInfo {

    public static void main(String[] args) {
        // final 字段修饰的东西，在java内存模型中存在一个jvm优化的过程
        Integer v1 = 7;
        Integer v2 = Integer.valueOf(7);
        Integer v3 = new Integer(7);
        System.out.println("v1 == v2 ? "+(v1 == v2)); // true
        System.out.println("v1 == v3 ? "+(v1 == v3)); // false
        System.out.println("v2 == v3 ? "+(v2 == v3)); // false
        System.out.println("v1 equals v2 ? "+(v1.equals(v2))); //true
        System.out.println("v1 equals v3 ? "+(v1.equals(v3))); //true
        System.out.println("v2 equals v3 ? "+(v2.equals(v3))); //true

        //问：如何不改代码，上面的结果都返回false？
        //答：通过调整java.lang.Integer.IntegerCache.high参数 JAVA_OPTS="-Djava.lang.Integer.IntegerCache.high=50" 还是 -XX:AutoBoxCacheMax=<size> ？
    }

}
