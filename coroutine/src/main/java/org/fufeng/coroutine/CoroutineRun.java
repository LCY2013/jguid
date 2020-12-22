/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-12-22
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.coroutine;

import kilim.Mailbox;
import kilim.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 模拟协程的高度碰撞
 * <p>
 * main总计花费时长：343
 * @create 2020-12-22
 */
public class CoroutineRun {

    static Map<Integer, Mailbox<Integer>> mailMap = new HashMap<Integer, Mailbox<Integer>>();

    public static void main(String[] args) {

        if (kilim.tools.Kilim.trampoline(false, args)) return;
        Properties propes = new Properties();
        propes.setProperty("kilim.Scheduler.numThreads", "8");
        System.setProperties(propes);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            Mailbox<Integer> mb = new Mailbox<Integer>(1, 10);
            new Producer(i, mb).start();
            mailMap.put(i, mb);
        }

        for (int i = 0; i < 1000; i++) {
            new Consumer(mailMap.get(i)).start();
        }

        Task.idledown();

        long endTime = System.currentTimeMillis();

        System.out.println(Thread.currentThread().getName() + "总计花费时长：" + (endTime - startTime));
    }

}
