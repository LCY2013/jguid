/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-18
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.project.base.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 字符串正则匹配
 * @create 2020-11-18
 */
public class StringRegex {

    public static void main(String[] args) {
        //regex1();
        //regex2();
        regex3();
    }

    /**
     * 测试正则表达式非捕获组
     * <p>
     * 减少不需要获取的分组，可以提高正则表达式的性能
     */
    private static void regex3() {
        String text = "<input high=\"20\" weight=\"70\">test</input>";
        String reg = "(?:<input.*?>)(.*?)(?:</input>)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(text);
        while (m.find()) {
            // 整个匹配到的内容
            System.out.println(m.group(0));
            //(.*?)
            System.out.println(m.group(1));
        }
    }

    /**
     * 测试正则表达式捕获组
     */
    private static void regex2() {
        String text = "<input high=\"20\" weight=\"70\">test</input>";
        String reg = "(<input.*?>)(.*?)(</input>)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(text);
        while (m.find()) {
            // 整个匹配到的内容
            System.out.println(m.group(0));
            //(<input.*?>)
            System.out.println(m.group(1));
            //(.*?)
            System.out.println(m.group(2));
            //(</input>)}
            System.out.println(m.group(3));
        }
    }

    /**
     * 测试 下面两种正则表达式模式区别
     * 贪婪模式（Greedy）
     * 独占模式（Possessive）
     */
    private static void regex1() {
        long start = System.nanoTime();
        String url = "https://www.baidu.com?q=java&o";
        //final String[] urls = url.split("\\?(([A-Za-z0-9-~_=%]++\\&{0,1})+)");
        final String[] urls = url.split("\\?(([A-Za-z0-9-~_=%]+\\&{0,1})+)");
        for (String u : urls) {
            System.out.println(u);
        }
        System.out.println(System.nanoTime() - start);
    }

}
