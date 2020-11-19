/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-19
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.project.base.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 测试ArrayList 遍历移除元素
 * @create 2020-11-19
 */
public class ListRemove {

    public static void main(String[] args) {
        final List<String> foreachList = new ArrayList<>();
        foreachList.add("1");
        foreachList.add("2");
        foreachList.add("3");
        foreachList.add("4");
        foreachList.add("5");

        //remove1(foreachList);

        remove2(foreachList);
    }

    private static void remove2(List<String> foreachList) {
        for (String str : foreachList) {
            if (str.equals("2")) {
                foreachList.remove(str);
            }
        }

        foreachList.forEach(System.out::println);
    }

    private static void remove1(List<String> foreachList) {
        final Iterator<String> iterator = foreachList.iterator();
        while (iterator.hasNext()) {
            final String str = iterator.next();
            if (str.equals("2")) {
                iterator.remove();
            }
        }
        foreachList.forEach(System.out::println);
    }


}
