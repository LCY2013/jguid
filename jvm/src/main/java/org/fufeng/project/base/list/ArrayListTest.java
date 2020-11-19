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

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description ArrayList测试
 * @create 2020-11-19
 */
public class ArrayListTest {

    /**
     *
     * @param DataNum
     */
    public static void addFromHeaderTest(int DataNum) {
        ArrayList<String> list = new ArrayList<String>(DataNum);
        int i = 0;

        long timeStart = System.currentTimeMillis();

        while (i < DataNum) {
            list.add(0,i+"aaavvv");
            i++;
        }
        long timeEnd = System.currentTimeMillis();

        System.out.println("ArrayList从集合头部位置新增元素花费的时间" + (timeEnd - timeStart));
    }


    /**
     *
     * @param DataNum
     */
    public static void addFromMidTest(int DataNum) {
        ArrayList<String> list = new ArrayList<String>(DataNum);
        int i = 0;

        long timeStart = System.currentTimeMillis();
        while (i < DataNum) {
            int temp = list.size();
            list.add(temp/2+"aaavvv");
            i++;
        }
        long timeEnd = System.currentTimeMillis();

        System.out.println("ArrayList从集合中间位置新增元素花费的时间" + (timeEnd - timeStart));
    }


    /**
     *
     * @param DataNum
     */
    public static void addFromTailTest(int DataNum) {
        ArrayList<String> list = new ArrayList<String>(DataNum);
        int i = 0;

        long timeStart = System.currentTimeMillis();

        while (i < DataNum) {
            list.add(i+"aaavvv");
            i++;
        }

        long timeEnd = System.currentTimeMillis();

        System.out.println("ArrayList从集合尾部位置新增元素花费的时间" + (timeEnd - timeStart));
    }


    /**
     *
     * @param DataNum
     */
    public static void deleteFromHeaderTest(int DataNum) {
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;

        while (i < DataNum) {
            list.add(i + "aaavvv");
            i++;
        }
        long timeStart = System.currentTimeMillis();
        i=0;

        while (i < DataNum) {
            list.remove(0);
            i++;
        }


        long timeEnd = System.currentTimeMillis();

        System.out.println("ArrayList从集合头部位置删除元素花费的时间" + (timeEnd - timeStart));
    }


    /**
     *
     * @param DataNum
     */
    public static void deleteFromMidTest(int DataNum) {
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        while (i < DataNum) {
            list.add(i + "aaavvv");
            i++;
        }
        long timeStart = System.currentTimeMillis();
        i=0;

        while (i < DataNum) {
            int temp = list.size();
            list.remove(temp/2);
            i++;
        }


        long timeEnd = System.currentTimeMillis();

        System.out.println("ArrayList从集合中间位置删除元素花费的时间" + (timeEnd - timeStart));
    }


    /**
     *
     * @param DataNum
     */
    public static void deleteFromTailTest(int DataNum) {
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        while (i < DataNum) {
            list.add(i + "aaavvv");
            i++;
        }

        long timeStart = System.currentTimeMillis();

        i=0;

        while (i < DataNum) {
            int temp = list.size();
            list.remove(temp-1);
            i++;

        }

        long timeEnd = System.currentTimeMillis();

        System.out.println("ArrayList从集合尾部位置删除元素花费的时间" + (timeEnd - timeStart));
    }


    /**
     *
     * @param DataNum
     */
    public static void getByForTest(int DataNum) {
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;

        while (i < DataNum) {
            list.add(i + "aaavvv");
            i++;
        }
        long timeStart = System.currentTimeMillis();

        for (int j = 0; j < DataNum; j++) {
            list.get(j);
        }

        long timeEnd = System.currentTimeMillis();

        System.out.println("ArrayList for(;;)循环花费的时间" + (timeEnd - timeStart));
    }


    /**
     *
     * @param DataNum
     */
    public static void getByIteratorTest(int DataNum) {
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;

        while (i < DataNum) {
            list.add(i + "aaavvv");
            i++;
        }
        long timeStart = System.currentTimeMillis();

        for (Iterator<String> it = list.iterator(); it.hasNext();) {
            it.next();
        }

        long timeEnd = System.currentTimeMillis();

        System.out.println("ArrayList 迭代器迭代循环花费的时间" + (timeEnd - timeStart));
    }

}
