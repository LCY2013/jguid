/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-14
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.module.java.info;

import java.util.logging.Logger;

/**
 * @program: jguid
 * @description: 函数编程主类
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-14
 */
public class FunctionMain {

    /**
     * before jdk 9   < 9
     * public : all
     * protected : 同包，同类
     * private : 同类
     *
     * after jdk 9  >= 9
     * 模块化
     */
    private static final Logger logger = Logger.getLogger("FunctionMain");

    public static void main(String[] args) {
        // byte(8) char(16) short(16) int(32) float(32) long(64) double(64)
        try {
            execute();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private static void execute() throws Throwable{
        logger.info("execute");
    }
}
