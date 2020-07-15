/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-15
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.function;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @program: jguid
 * @description: {@link Supplier} 设计
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-15
 */
public class SupplierDesignInfo {

    public static void main(String[] args) {
        echo("fufeng"); //固定参数

        echo(() -> {
            sleep(100);
            return "magic="+System.currentTimeMillis();
        });

        echo(SupplierDesignInfo::getMessage);

        getMessage(); //及时返回数据

        final Supplier<String> stringSupplier = supplyMessage();//待执行数据
        System.out.println((stringSupplier.get())); //实际获取
    }

    private static Supplier<String> supplyMessage(){
        return SupplierDesignInfo::getMessage;
    }

    private static String getMessage(){
        sleep(500);
        return "magic-"+System.currentTimeMillis();
    }

    private static void sleep(long millis){
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void echo(String message){ // 拉模式
        System.out.println(message);
    }

    private static void echo(Supplier<String> message){ // 推模式
        System.out.println((message.get()));
    }
}
