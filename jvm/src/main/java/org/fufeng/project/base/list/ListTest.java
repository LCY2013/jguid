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

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 比较ArrayList和LinkedList 相关操作的性能
 * @create 2020-11-19
 */
public class ListTest {

    public static void main(String[] args) {

        // List头部添加测试
        ArrayListTest.addFromHeaderTest(100000);
        LinkedListTest.addFromHeaderTest(100000);

        // List中部添加测试
        ArrayListTest.addFromMidTest(10000);
        LinkedListTest.addFromMidTest(10000);

        // 在尾部添加测试
        ArrayListTest.addFromTailTest(1000000);
        LinkedListTest.addFromTailTest(1000000);

        // 从头部删除测试
        ArrayListTest.deleteFromHeaderTest(100000);
        LinkedListTest.deleteFromHeaderTest(100000);

        // 从中部删除测试
        ArrayListTest.deleteFromMidTest(100000);
        // 性能极差
        LinkedListTest.deleteFromMidTest(100000);

        // 从尾部删除测试
        ArrayListTest.deleteFromTailTest(1000000);
        LinkedListTest.deleteFromTailTest(1000000);

        // for循环遍历
        ArrayListTest.getByForTest(10000);
        LinkedListTest.getByForTest(10000);

        // iterator循环遍历
        ArrayListTest.getByIteratorTest(100000);
        LinkedListTest.getByIteratorTest(100000);

    }

}
