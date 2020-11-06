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
package org.fufeng.concurrent.cases.reconciliation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 模拟数据源操作
 * @create 2020-11-06
 */
public class DBUtils {

    /**
     *  格式化日期
     */
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 模拟是否还存在未对账订单信息
     *
     * @return 存在 {@code true} 不存在 {@code false}
     */
    public boolean hasNext() {
        return new Random().nextInt() % 2 == 0;
    }

    /**
     * 模拟获取未对账订单信息
     *
     * @return 未对账订单信息
     */
    public Object getPOrders() {
        print("getPOrders");
        return new Object();
    }

    /**
     * 模拟获取派送单信息
     *
     * @return 派送单信息
     */
    public Object getDOrders() {
        print("getDOrders");
        return new Object();
    }

    /**
     * 模拟对账处理
     *
     * @param pObj 未对账订单信息
     * @param dObj 派送单信息
     * @return 存在异常数据
     */
    public Object diff(Object pObj, Object dObj) {
        print("diff");
        return new Object();
    }

    /**
     *  存储异常的对账数据信息
     * @param diff 异常的对账数据
     */
    public void save(Object diff) {
        print("save");
    }

    /**
     *  打印数据
     * @param message 需要打印的消息
     */
    public void print(String message){
        System.out.printf("%s ， Thread-[%s]，exec %s\n",simpleDateFormat.format(new Date()),Thread.currentThread().getName(),message);
    }

    public static String intToString(Integer coverValue){
        return String.format("%d",coverValue);
    }

    public static void main(String[] args) {
        Function<Integer,String> function = DBUtils::intToString;

        function.apply(1);
    }

}
