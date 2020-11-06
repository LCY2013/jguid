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
package org.fufeng.concurrent.cases.pool;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 利用信号量限制对象池对象的使用
 * @create 2020-11-05
 */
public class ObjPool<T, R> {

    /**
     * 构造对象池
     */
    private final List<T> objPool;

    /**
     * 信号量
     */
    private final Semaphore semaphore;

    /**
     * 初始化构造一个对象池
     *
     * @param size 信号量的大小
     * @param objs 对象池数据
     */
    @SafeVarargs
    public ObjPool(int size, T... objs) {
        // 条件检测
        if (size > objs.length) {
            throw new IllegalArgumentException("object pool size should not less than semaphore size");
        }
        // 初始化对象池
        //this.objPool = new ArrayList<>(Arrays.asList(objs));
        this.objPool = new Vector<>(Arrays.asList(objs));
        // 初始化信号量 PV
        semaphore = new Semaphore(size);
    }

    /**
     * 利用对象池的对象调用func方法
     *
     * @param func 方法
     * @return {@link R}
     */
    R exec(Function<T, R> func) {
        T t = null;
        try {
            // 申请信号量
            semaphore.acquire();

            // 获取对象池中的第一个元素
            t = objPool.remove(0);

            // 使用方法调用
            return func.apply(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放信号量
            semaphore.release();
            // 将对象归还给对象池
            objPool.add(t);
        }
        return null;
    }

    public static void main(String[] args) {
        // 创建测试用例
        final ObjPool<Long, String> objPool = new ObjPool<>(5, 1L, 2L, 3L, 4L, 5L);

        //objPool.exec(ObjPool::longToString);

        for (int i = 0; i < 10; i++) {
            new Thread(()-> objPool.exec(param -> {
                System.out.println(param);
                return String.format("%d", param);
            })).start();
        }

    }

    /**
     * Long - String
     *
     * @param param 参数
     * @return {@code String}
     */
    private static String longToString(Long param) {
        System.out.println(param);
        return String.format("%d", param);
    }

}
