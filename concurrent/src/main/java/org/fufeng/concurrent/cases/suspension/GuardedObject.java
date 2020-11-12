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
package org.fufeng.concurrent.cases.suspension;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 经典的管程模式
 * @create 2020-11-12
 */
public class GuardedObject<T> {

    /**
     * 保管所有的GuardedObject
     */
    private final static Map<Object, GuardedObject> guardedObjectMap = new ConcurrentHashMap<>();

    /**
     * 定义结果对象
     */
    private T result;

    /**
     *  请求id
     */
    private Long reqId;

    /**
     * 定义可重入锁
     */
    private final ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * 定义条件
     */
    private final Condition condition = reentrantLock.newCondition();

    /**
     *  请求开始的时间
     */
    private Date startRequestTime;

    /**
     * 设置请求超时时间
     */
    private final long timeout = 3000;

    /**
     * 定义获取方式
     *
     * @param predicate 自定义断言
     * @return 结果
     */
    public T get(Predicate<T> predicate) {
        reentrantLock.lock();
        try {
            // MESA 管程经典写法
            while (!predicate.test(this.result)) {
                // 是否超时
                if (System.currentTimeMillis() - this.startRequestTime.getTime() > timeout) {
                    System.out.printf("%d 请求超时\n",this.reqId);
                    guardedObjectMap.remove(this.reqId);
                    break;
                }
                condition.await(timeout,TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        return this.result;
    }

    /**
     * 定义获取方式
     *
     * @param predicate 自定义断言
     * @param timeout   超时时间
     * @param timeUnit  超时计时单位
     * @return 结果
     */
    public T get(Predicate<T> predicate, int timeout, TimeUnit timeUnit) {
        reentrantLock.lock();
        try {
            // MESA 管程经典写法
            while (!predicate.test(this.result)) {
                // 是否超时
                if (System.currentTimeMillis() - this.startRequestTime.getTime() > timeout) {
                    guardedObjectMap.remove(this.reqId);
                    break;
                }
                condition.await(timeout, timeUnit);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        // 返回已经接受到结果集的结果
        return this.result;
    }

    /**
     * 事件通知方法
     *
     * @param object 结果
     */
    public void onChange(T object) {
        reentrantLock.lock();
        try {
            this.result = object;
            condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * 向容器中添加GuardedObject
     *
     * @param reqId 请求id
     */
    public static <T> GuardedObject<T> newGuardedObject(long reqId) {
        final GuardedObject<T> guardedObject = new GuardedObject<>();
        guardedObject.startRequestTime = new Date();
        guardedObject.reqId = reqId;
        guardedObjectMap.put(reqId, guardedObject);
        return guardedObject;
    }

    /**
     * 获取容器中的GuardedObject
     *
     * @param reqId 请求id
     * @return GuardedObject
     */
    public static <K, T> void fireEvent(K reqId, T message) {
        final GuardedObject<T> guardedObject = guardedObjectMap.remove(reqId);
        if (Objects.nonNull(guardedObject)) {
            guardedObject.onChange(message);
        }
    }

}
