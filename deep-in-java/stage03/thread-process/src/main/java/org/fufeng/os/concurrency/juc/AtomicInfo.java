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

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: jguid
 * @description: Atomic 原子类操作
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-23
 */
public class AtomicInfo {

    public static void main(String[] args) throws PrivilegedActionException {
        // CAS 是一种CPU原语性的设置操作，相对较重的比较，可以实现轻量级锁
        // 即用自旋可以实现相对较轻的锁，比较消耗CPU资源，但是在线程较多的情况下不建议使用

        getUnsafe();

        // volatile 修饰复杂对象，不具备保证对象的安全性
    }

    /**
     *  Long 原子操作类
     */
    private static void atomicLong(){
        AtomicLong atomicLong = new AtomicLong();
        /*
            AtomicLong 内部是一个private volatile long value;
            get() 和 set() 也是volatile原语
            compareAndSet() 利用工具: Unsafe
            long 8字节
            是否安全呢？
            32位操作系统 64位 需要两次操作所以不是线程安全的
            64位操作系统 64位 只需一次操作所以是线程安全的

            set 和 get 是通过JVM中unsafe 魔法类去直接使用操作系统CPU指令
         */
    }

    /**
     *  Integer 原子操作类
     */
    private static void atomicInteger(){
        AtomicInteger atomicInteger = new AtomicInteger();
        /*
            AtomicInteger 内部是一个private volatile int value;
            get() 和 set() 也是volatile原语
            compareAndSet() 利用工具: Unsafe
         */
    }

    /**
     *  Boolean原子操作
     */
    private static void atomicBoolean(){
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        /*
            AtomicBoolean 内部是一个private volatile int value;
            用int类型的value表示状态，1 : true   0 : false
            其中 get() 和 set() 还是来自于volatile 原语
            compareAndSet() 才是利用了CPU的原语指令保证原子性
            具体实现CAS工具:
            jdk8  : unsafe
            jdk9+ : VarHandle
         */
    }

    /**
     *  获取到VarHandle
     *  要求: > jdk9
     */
    private static void getVarHandle(){
        final MethodHandles.Lookup lookup = MethodHandles.lookup();
    }

    /**
     *  获取魔法工具
     */
    private static void getUnsafe() throws PrivilegedActionException {
        // 默认情况下 抛出java.lang.SecurityException: Unsafe
        // final Unsafe unsafe = Unsafe.getUnsafe();

        /*PrivilegedExceptionAction<Unsafe> privilegedExceptionAction =
                new PrivilegedExceptionAction<Unsafe>(){
                    @Override
                    public Unsafe run() throws Exception {
                        // 放射获取theUnsafe字段
                        final Field theUnsafe =
                                Unsafe.class.getDeclaredField("theUnsafe");
                        // 设置字段可见
                        theUnsafe.setAccessible(true);
                        return (Unsafe) theUnsafe.get(null);
                    }
                };*/
        PrivilegedExceptionAction<Unsafe> privilegedExceptionAction =
                () -> {
                    // 放射获取theUnsafe字段
                    final Field theUnsafe =
                            Unsafe.class.getDeclaredField("theUnsafe");
                    // 设置字段可见
                    theUnsafe.setAccessible(true);
                    return (Unsafe) theUnsafe.get(null);
                };
        // 获取到魔法工具
        final Unsafe unsafe =
                AccessController.doPrivileged(privilegedExceptionAction);
        if (Objects.isNull(unsafe)){
            throw new NullPointerException("unsafe 获取失败");
        }
        System.out.println(unsafe);
    }

}
