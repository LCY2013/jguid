/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-10
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.pool.v5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 利用FutureTask 进行任务协作
 * @create 2020-11-10
 * @see FutureTask
 */
public class ThreadPoolV5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 取茶流程
        final FutureTask<String> teaTask = new FutureTask<>(new TeaFuture());
        // 烧水流程
        final FutureTask<String> waterTask = new FutureTask<>(new WaterFuture(teaTask));

        new Thread(teaTask).start();
        new Thread(waterTask).start();

        final String waterResult = waterTask.get();

        System.out.println(waterResult);
    }

}
