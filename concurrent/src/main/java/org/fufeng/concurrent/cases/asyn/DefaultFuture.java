/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-05
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.asyn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 模拟Dubbo 异步获取响应
 *      Dubbo 异步转同步操作
 * @create 2020-11-05
 */
public class DefaultFuture<T> {

    /**
     *  定义互斥 可重入锁
     */
    private final Lock lock = new ReentrantLock();

    /**
     *  定义可重入锁的条件变量
     */
    private final Condition done = lock.newCondition();

    /**
     *  异步响应信息
     */
    private T response;

    /**
     *  获取服务异步响应
     * @param timeout 获取响应超时时间
     * @return 响应信息转换后的数据实体
     */
    public T get(int timeout){
        // 记录获取起始时间
        long startTime = System.currentTimeMillis();
        lock.lock();
        try {
            while (!isDone()){
                // 条件等待
                done.await(timeout, TimeUnit.MILLISECONDS);

                // 获取当前等待后的时间
                long currentTime = System.currentTimeMillis();

                // 满足条件退出循环
                if (isDone() || currentTime - startTime > timeout){
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        // 超时还是未响应
        if (!isDone()){
            throw new RuntimeException("rpc request timeout");
        }

        return response;
    }

    /**
     *  是否已经获取RPC响应
     * @return 已经响应 {@code true} 未响应 {@code false}
     */
    private boolean isDone() {
        return response != null;
    }

    /**
     *  RPC 服务调用后返回数据会调用该方法
     * @param response 返回的响应数据
     */
    public void doReceived(T response){
        lock.lock();
        try {
            if (response == null){
                throw new RuntimeException("received response can't null");
            }

            this.response = response;

            // 唤醒等待的线程
            done.signal();
        }finally {
            lock.unlock();
        }
    }

}
