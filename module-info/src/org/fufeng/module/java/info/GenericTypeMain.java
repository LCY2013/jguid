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
package org.fufeng.module.java.info;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @program: jguid
 * @description: 类型转换
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-14
 */
public class GenericTypeMain {

    public static void main(String[] args) {
        Converter<String,Integer> converter =
                new Converter<String, Integer>() {
                    @Override
                    public Integer convert(String source) {
                        return Integer.valueOf(source);
                    }
                };
        System.out.println((converter.convert("21")));

        Converter<Integer,String> converterStrToInt = String::valueOf;
        System.out.println((converterStrToInt.convert(90)));

        List<String> listStr = Collections.emptyList();
        List list = Collections.emptyList();
        listStr = list;
    }

    //转换服务定义抽象
    public interface Converter<S,T extends Serializable>{
        T convert(S source);
    }

    public interface StringConverter<T extends Serializable> extends Converter<String,T>{

    }

}
