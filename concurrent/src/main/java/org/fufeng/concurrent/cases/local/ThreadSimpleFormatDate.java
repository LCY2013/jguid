/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-12
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.local;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 通用日期格式化组件
 * @create 2020-11-12
 */
public class ThreadSimpleFormatDate {

    /**
     * 模拟通过ThreadLocal获取日期格式化工具
     */
    private final static ThreadLocal<SimpleDateFormat> sdf =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    /**
     * 通过ThreadLocal获取日期格式化组件
     *
     * @return 日期格式化组件
     */
    public static SimpleDateFormat getDateFormat() {
        return sdf.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(4);
        final List<SimpleDateFormat> sdfList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                final SimpleDateFormat simpleDateFormat = sdf.get();
                sdfList.add(simpleDateFormat);
                print(simpleDateFormat, simpleDateFormat.format(new Date()));
                countDownLatch.countDown();
            }).start();
        }
        System.out.printf("%s\n", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        countDownLatch.await();
        System.out.println(sdfList.get(0)==sdfList.get(1));
        System.out.println(sdfList.get(1)==sdfList.get(2));
        System.out.println(sdfList.get(2)==sdfList.get(3));
    }

    private static void print(Object sdf, String message) {
        System.out.printf("[%s],%s,%s\n", Thread.currentThread().getName(), sdf, message);
    }
}
