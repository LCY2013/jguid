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
package org.fufeng.concurrent.cases.deallock.dealwith.three;

import java.math.BigDecimal;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 账户实体类
 *  解决循环等待问题
 *
 *  通过对资源进行大小排序，按大小排序进行上锁操作
 * @create 2020-10-29
 */
public class Account implements Comparable{

    /**
     * 用于资源排序字段
     */
    private int sort;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 构建一个账户生成一个带有余额的账户信息
     *
     * @param balance 余额
     */
    public Account(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 转账操作
     *
     * @param target 转账的目标账户
     * @param amt    转账金额
     * @return 是否转账成功 成功 {@code true}  失败 {@code false}
     */
    public boolean transform(Account target, BigDecimal amt) {
        // 定义小的账户
        Account min = this;
        Account max = target;
        // 排序比较两个对象的排序大小
        if (min.compareTo(max) > 0){
            min = target;
            max = this;
        }
        // 先锁定小账户
        synchronized (min) {
            // 再锁定大账户
            synchronized (max) {
                this.balance = this.balance.subtract(amt);
                target.balance = target.balance.add(amt);
            }
        }
        return true;
    }

    /**
     *  排序
     * @param completeObj 比较的字段
     * @return 大于 {@code >0} 等于 {@code =0} 小于 {@code <0}
     */
    @Override
    public int compareTo(Object completeObj) {
        // 优先判断是不是Account类型
        if (completeObj instanceof Account){
            Account account = Account.class.cast(completeObj);
            return this.sort - account.sort;
        }
        return 0;
    }
}
