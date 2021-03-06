/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-08-04
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.io;

import java.util.stream.IntStream;

/**
 * @program: jguid
 * @description:
 *    装饰器模式/包装模式
 * 当前装饰器模式实现类只有扩展不重要（并非无意义）的特性（方法），核心特性来自于继承的类或者接口（实际来源外部注入）
 * <p>
 * A 类型提供了 N 个方法（特性）
 * A 类型的装饰对象（Decorator）继承 A 类型
 * 代理对象（delegate） is A 类型
 * 装饰对象（Decorator）不一定完全按照代理对象（delegate）的行为来执行
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-04
 */
public class DecoratingCharSequence implements CharSequence {

    private final CharSequence delegate;

    public DecoratingCharSequence(CharSequence delegate) {
        this.delegate = delegate;
    }

    @Override
    public int length() {
        return this.delegate.length();
    }

    @Override
    public char charAt(int index) {
        return this.delegate.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this.delegate.subSequence(start,end);
    }

    @Override
    public IntStream chars() {
        return this.delegate.chars();
    }

    @Override
    public IntStream codePoints() {
        return this.delegate.codePoints();
    }

    @Override
    public String toString() {
        return "@"+super.toString();
    }
}
