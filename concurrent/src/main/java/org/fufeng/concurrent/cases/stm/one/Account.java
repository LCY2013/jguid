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
package org.fufeng.concurrent.cases.stm.one;

import org.multiverse.api.StmUtils;
import org.multiverse.api.references.TxnLong;

import static org.multiverse.api.StmUtils.atomic;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 用户类型，利用STM(Multiverse Software Transactional Memory)操作
 * @create 2020-11-17
 */
public class Account {

    /**
     * 余额
     */
    private TxnLong balance;

    public Account(long balance) {
        this.balance = StmUtils.newTxnLong(balance);
    }

    /**
     * 转账给某个用户
     *
     * @param to  转账到某个用户
     * @param amt 金额
     */
    public void transfer(Account to, int amt) {
        // 原子化操作
        atomic(() -> {
            if (this.balance.get() > amt) {
                this.balance.decrement(amt);
                to.balance.increment(amt);
            }
        });
    }
}
