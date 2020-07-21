/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-21
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.collection.advanced;

import java.util.Arrays;

/**
 * @program: jguid
 * @description: 数组的不可变性
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-21
 */
public class ImmutableArrayInfo {

    public static void main(String[] args) {
        // values object address : 100
        Integer[] values = of(1, 2, 3, 4, 5, 6, 7);
        /*
            数组特点，数组长度不变，内容可以被替换
            values[0] = 1
            values[1] = 9
            values[2] = 9
            values[3] = 5
         */
        // 数组的copy与集合的copy类似，都是浅克隆
        final Integer[] copyValues = Arrays.copyOf(values, values.length);

        // 比较copy和原数组的元素是否一致
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i] == copyValues[i]);
        }

        // 修改复制后的内容
        copyValues[0] = 128;
        // 输出原始数组
        System.out.println(Arrays.toString(values));

        // 开辟3个元素大小的数组空间,类似与C语言的malloc
        Integer[] newInteger = new Integer[3];
        //newInteger[0] = null;
        //newInteger[1] = null;
        //newInteger[2] = null;

        final User[] users = of(1L, 2L, 3L);
        final User[] copyUsers = Arrays.copyOf(users, users.length);
        // 这里这个copyUsers作为返回值，是否会存在安全问题

        // 修改其中一个User信息
        copyUsers[1].setId(7L);
        // 输出users信息，结果显示users的信息被修改
        System.out.println(Arrays.toString(users));

        /*
            1、数组的拷贝在这里也是浅拷贝(和对象的clone一样)
            2、如果存在安全问题，则需要利用到深度克隆
         */
    }

    private static User[] of(Long... ids) {
        User[] users = new User[ids.length];
        for (int i = 0; i < ids.length; i++) {
            users[i] = new User(ids[i]);
        }
        return users;
    }

    private static class User {
        private long id;

        public User(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    '}';
        }
    }

    private static Integer[] of(Integer... ints) {
        return ints;
    }

}
