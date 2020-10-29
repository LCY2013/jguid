/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-10-29
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.waitnotify.one;

import java.math.BigDecimal;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 账户实体类
 * 解决占有且等待问题
 * @create 2020-10-29
 * @see Allocator 资源申请表
 */
public class Account {

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 资源申请器
     */
    private Allocator allocator;

    /**
     * 构建一个账户生成一个带有余额的账户信息
     *
     * @param balance 余额
     */
    public Account(BigDecimal balance, Allocator allocator) {
        this.balance = balance;
        this.allocator = allocator;
    }

    /**
     * 转账操作
     *
     * @param target 转账的目标账户
     * @param amt    转账金额
     * @return 是否转账成功 成功 {@code true}  失败 {@code false}
     */
    public boolean transform(Account target, BigDecimal amt) {
        // 一致性申请所有的资源
        allocator.apply(this, target);
        try {
            // 锁定转出账户
            synchronized (this) {
                // 锁定转入账户
                synchronized (target) {
                    if (this.balance.compareTo(amt) >= 0) {
                        this.balance = this.balance.subtract(amt);
                        target.balance = target.balance.add(amt);
                    }
                }
            }
        } finally {
            // 释放持有的所有资源
            allocator.free(this, target);
        }
        return true;
    }

}
