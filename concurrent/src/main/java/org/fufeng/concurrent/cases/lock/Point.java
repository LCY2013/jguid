/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-06
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 封装一个二维坐标信息
 *  官方demo
 * @create 2020-11-06
 */
public class Point {

    /**
     * 定义二维坐标的横纵坐标
     */
    protected volatile double x, y;

    /**
     * 定义 {@link StampedLock}
     */
    private final StampedLock stampedLock = new StampedLock();

    /**
     * 计算某个点到原点的距离
     *
     * @return 距离
     */
    public double distanceFromOrigin() {
        // 申请乐观锁
        final long optimisticRead = stampedLock.tryOptimisticRead();
        // 读取局部变量，可能会在这个过程中被篡改
        double x = this.x, y = this.y;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 先判断执行负值期间是否存在写操作，如果存在那下面的校验就会返回false
        if (!stampedLock.validate(optimisticRead)) {

            // 升级为悲观读锁
            final long readLock = stampedLock.readLock();
            try {
                System.out.println("乐观锁升级");
                x = this.x;
                y = this.y;
            }finally {
                // 释放悲观锁
                stampedLock.unlockRead(readLock);
            }
        }

        // 没有被更改过就直接进行计算
        return Math.sqrt(x * x + y * y);
    }

    /**
     *  设置写操作
     * @param x 横坐标
     * @param y 纵坐标
     */
    public void set(double x,double y){
        final long writeLock = stampedLock.writeLock();
        try {
            this.x = x;
            this.y = y;
            System.out.println("set success");
        }finally {
            stampedLock.unlockWrite(writeLock);
        }
    }
}
