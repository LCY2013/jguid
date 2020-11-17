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
 * @description 自定义STM 版本的转账操作
 * @create 2020-11-17
 */
public class Account {

    /**
     * 余额
     */
    private TxnRef<Long> balance;

    public Account(long balance) {
        this.balance = new TxnRef<>(balance);
    }

    /**
     * 转账给用个用户
     *
     * @param to  转账到该用户
     * @param amt 转账金额
     */
    public void transfer(Account to, long amt) {
        STM.atomic((tnx) -> {
            // 现在该事务中查询转账人的余额
            final Long from = balance.getValue(tnx);
            if (from >= amt) {
                balance.setValue(tnx,from-amt);
                final Long toBalance = to.balance.getValue(tnx);
                to.balance.setValue(tnx,toBalance+amt);
            }
        });
    }

    public static void main(String[] args) {
        
    }
}
