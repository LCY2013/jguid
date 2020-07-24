/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-23
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.concurrency.juc;

import java.util.concurrent.locks.Lock;

/**
 * @program: jguid
 * @description: {@link Lock} juc
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-23
 */
public class AcquireAndReleaseInfo {

    public static void main(String[] args) {
        /*
           Lock 接口抽象
                获取 Acquire
                    -> 等价于 Synchronized 获取到Monitor监视器锁
                释放 Release
                    ->  1. 当 Thread（hold lock），调用 Object#wait() 时候
                        2. Thread park -> LockSupport.park(Object)
                        3. Condition#await()
                        4. 运行期异常，Thread 消亡
                        5. Java 9 自旋 Thread.onSpinWait();
                        6. Thread.yield();

                所谓的公平（Fair）和非公平（NonFair、unfair）
                公平（Fair）线程 FIFO
                非公平（NonFair）线程随线程调度
                最佳实践：在创建线程时，除非必要，不要调整线程优先级（Priority）
         */
    }

}
