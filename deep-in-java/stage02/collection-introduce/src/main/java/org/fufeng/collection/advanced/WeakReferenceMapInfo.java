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

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @program: jguid
 * @description: {@link WeakReference} 弱引用
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-21
 */
public class WeakReferenceMapInfo {

    public static void main(String[] args) throws InterruptedException {
        // 弱引用HashMap
        infoWeakHashMap();
    }

    private static void infoWeakHashMap() throws InterruptedException {
        // 强引用
        // value 变量是局部变量，存放在栈
        // "abc" 是常量，在 Java 8 之前是放在 Perm 区域，Java 8+ 是存放在 META 区域
        // 在 demoWeakHashMap() 方法执行结束后，value 变量会被立即回收，"abc" 常量常驻
        String value = "abc";

        // 定义引用队列
        ReferenceQueue<User> referenceQueue = new ReferenceQueue<>();
        // 定义弱引用，WeakReference继承自Reference,当GC过时，会将这个引用加入到引用队列里
        WeakReference<User> weakReference = new WeakReference<User>(new User("fufeng"), referenceQueue);

        // 通过引用对象获取User信息
        for (int i = 1; i < 100; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(weakReference.get());
            if (i % 5 == 0) {
                System.gc();
            }
        }
    }

    private static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
