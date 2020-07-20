/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-20
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.collection;

import java.util.Map;

/**
 * @program: jguid
 * @description: {@link Map} map实现
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-20
 */
public class MapInfo {

    public static void main(String[] args) {
        Map<Integer,String> map = Map.of(1,"fufeng");
        System.out.println(map.get(1));
        System.out.println(map.get(1L));
        System.out.println(map.get(1.0));
        System.out.println(map.get(new Key(1)));
        System.out.println(map.containsKey(new Key(1)));
    }

    private static class Key{
        private final int value;

        public Key(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this){
                return true;
            }
            if(obj instanceof Integer){
                // jdk1.5以后会有自动拆箱装箱的操作
                return this.value == (Integer) obj;
            }
            if(obj instanceof Key){
                Key key = (Key) obj;
                return this.value == key.value;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return value;
        }
    }
}
