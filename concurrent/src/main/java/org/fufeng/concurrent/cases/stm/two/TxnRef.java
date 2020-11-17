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

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 支持事务相关的引用
 * @create 2020-11-17
 */
public class TxnRef<T> {

    /**
     * 当前带有版本号的数据
     */
    protected VersionedRef<T> curRet;

    /**
     * 构造函数
     *
     * @param curRet 实际数据
     */
    public TxnRef(T curRet) {
        this.curRet = new VersionedRef(curRet, 0L);
    }

    /**
     * 获取事务当前的数据值
     *
     * @param tnx 事务抽象
     * @return 事务数据
     */
    public T getValue(Tnx tnx) {
        return tnx.get(this);
    }

    /**
     * 设置事务的数据值
     * @param tnx 事务抽象
     * @param value 需要设置的事务值
     */
    public void setValue(Tnx tnx, T value) {
        tnx.set(this,value);
    }
}
