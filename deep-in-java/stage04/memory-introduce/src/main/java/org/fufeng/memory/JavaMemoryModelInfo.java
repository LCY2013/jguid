/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-27
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.memory;

/**
 * @program: jguid
 * @description: JMM java中内存模型
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-27
 */
public class JavaMemoryModelInfo {

    /**
     * 广义同步：狭义锁（互斥）、volatile 以及原子操作（Unsafe）
     * Java9+ VarHandle
     */

    /**
     * 狭义锁（互斥）：
     *  OS 原语（Windows）：
     *      HANDLE mutex = CreateMutex(NULL, FALSE, NULL);
     *      CRITICAL_SECTION critSec;
     *  POSIX Thread 等高级 API：
     *      pthread_mutex_t 数据结构
     */

    /**
     * volatile
     * 确保：
     *      变量的可见性
     *      引用的原子性：https://docs.oracle.com/javase/tutorial/essential/concurrency/atomic.html
     * 实现：
     *      大部分利用 C++ volatile 编译时限制重排（内存屏障）
     *          Memory Barriers：https://www.infoq.com/articles/memory_barriers_jvm_concurrency
     *      部分通过汇编实现
     *      源码快速路径：orderAccess.hpp
     *
     */

    /**
     * 原子操作（Atomic）
     * 确保：
     *      变量的原子操作（自增长、exchange、CAS 等操作）
     * 实现：
     *      利用汇编指令
     *      源码快速路径：atomic.hpp
     */
}
