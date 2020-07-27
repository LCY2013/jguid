/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-24
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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * @program: jguid
 * @description: {@link ArrayBlockingQueue} 阻塞队列示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-24
 * @see ArrayBlockingQueue
 */
public class ArrayBlockingQueueInfo {

    public static void main(String[] args) {
        // ArrayBlockingQueue 是有限队列
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        // offer 操作 队列满时就忽略
        //offerMethod(queue);

        // add 操作 队列满时就抛出异常
        // addMethod(queue);
        
        // put 操作 线程阻塞等待
        putMethod(queue);

        // 打印队列内容
        System.out.println(queue);
    }

    /**
     *  通过put向集合中添加元素
     * @param queue 队列
     */
    private static void putMethod(ArrayBlockingQueue<Integer> queue) {
        try {
            queue.put(1);
            queue.put(2);
            // 超过了队列容量？会发送什么? 会出发notFull的wait()等待操作
            queue.put(3);
            queue.put(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *  通过add方法操作队列
     * @param queue 队列
     */
    private static void addMethod(ArrayBlockingQueue<Integer> queue) {
        queue.add(1);
        queue.add(2);
        // 如果这里超过了容量？会发生什么？ java.lang.IllegalStateException: Queue full,由于没有添加进队列
        queue.add(3);
        queue.add(4);
    }

    /**
     *  通过offer操作方法
     * @param queue 队列
     */
    private static void offerMethod(BlockingQueue<Integer> queue){
        queue.offer(1);
        queue.offer(2);
        // 如果添加的元素超过了队列的容量，那么后面的offer就会被忽略
        queue.offer(3);
        queue.offer(4);
    }

}
