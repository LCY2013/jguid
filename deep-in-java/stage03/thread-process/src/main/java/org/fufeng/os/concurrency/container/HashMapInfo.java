/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-27
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.concurrency.container;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: jguid
 * @description: {@link HashMap} HashMap 使用示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-27
 * @see HashMap
 * @see Map
 */
public class HashMapInfo {

    public static void main(String[] args) {
        String strValue = "fufeng";
        Integer intValue = strValue.hashCode();

        HashMap<Object,Object> hashMap = new HashMap<>();
        //hashMap.put(strValue,1);
        //hashMap.put(intValue,2);
        hashMap.put(new User(),3);
        hashMap.put(new Person(),4);

        hashMap.forEach((k,v) -> System.out.printf("%s -> %s\n",k,v));
    }

    static class User{

        @Override
        public int hashCode() {
            System.out.println("user-1");
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            System.out.println("user-2");
            if (obj instanceof Person){
                return true;
            }
            return super.equals(obj);
        }
    }

    static class Person{
        @Override
        public int hashCode() {
            System.out.println("person-1");
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            System.out.println("person-2");
            if (obj instanceof User){
                return true;
            }
            return super.equals(obj);
        }
    }

}
