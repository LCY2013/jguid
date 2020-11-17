/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-17
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.stm.two;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description STM 事务具体实现类
 * @create 2020-11-17
 * @see Tnx 事务抽象
 */
public final class STMTxn implements Tnx {

    /**
     * 事务ID生成器
     */
    private static AtomicLong txnSeq = new AtomicLong(0);

    /**
     * 当前事务中所有读写的数据的快照
     */
    private Map<TxnRef, VersionedRef> inTxnMap = new HashMap<>();

    /**
     * 当前事务需要写入的数据
     */
    private Map<TxnRef, Object> writeMap = new HashMap<>();

    /**
     * 当前事务的ID
     */
    private long txnId;

    /**
     * 构造方法用于初始化当前事务的事务ID
     */
    public STMTxn() {
        this.txnId = txnSeq.incrementAndGet();
    }

    /**
     * 获取当前事务中的数据
     * <p>
     * 读取数据作为快照放入 inTxnMap
     * <p>
     * 保证每次读取的数据都是一个版本
     *
     * @param ref 事务引用
     * @param <T> 具体数据类型
     * @return T
     */
    @Override
    public <T> T get(TxnRef<T> ref) {
        // 将需要读的数据加入到inTxnMap
        if (!inTxnMap.containsKey(ref)) {
            inTxnMap.put(ref, ref.curRet);
        }
        return (T) inTxnMap.get(ref).value;
    }

    /**
     * 设置当前事务中的数据
     * <p>
     * 写入的数据放入 writeMap
     * <p>
     * 写入的数据没被读取过，也会将其放入 inTxnMap
     *
     * @param ref   事务引用
     * @param value 需要在事务中设置的数据
     * @param <T>   具体数据类型
     */
    @Override
    public <T> void set(TxnRef<T> ref, T value) {
        // 将需要修改的数据加入到inTxnMap
        if (!inTxnMap.containsKey(ref)) {
            inTxnMap.put(ref, ref.curRet);
        }
        writeMap.put(ref, value);
    }

    /**
     * 提交事务相关
     *
     * @return 成功 {@code ture} 失败 {@code false}
     */
    public boolean commit() {
        // 串行化事务提交
        synchronized (STM.commitLock) {
            // 是否提交成功
            boolean isValid = true;

            // 校验所有读过的数据是否发生更改
            for (Map.Entry<TxnRef, VersionedRef> entry : inTxnMap.entrySet()) {
                // 事务版本引用
                final VersionedRef curRet = entry.getKey().curRet;
                // 版本引用
                final VersionedRef versionedRef = entry.getValue();
                if (curRet != versionedRef) {
                    isValid = false;
                    break;
                }
            }

            // 校验通过，所有信息都进行更改
            if (isValid) {
                writeMap.forEach((k,v)->
                    k.curRet = new VersionedRef(v,txnId));
            }

            return isValid;
        }
    }

}
