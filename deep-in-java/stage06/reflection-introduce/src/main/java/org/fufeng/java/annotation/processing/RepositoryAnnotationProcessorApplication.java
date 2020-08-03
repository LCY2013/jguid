/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-08-03
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.java.annotation.processing;

import java.io.File;
import java.io.IOException;

import org.fufeng.java.annotation.compiler.Compiler;

import static org.fufeng.java.annotation.compiler.CompilerInfo.getClassOutputDirectory;

/**
 * @program: jguid
 * @description: 编译 + {@link RepositoryAnnotationProcessor}处理
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-03
 */
public class RepositoryAnnotationProcessorApplication {

    public static void main(String[] args) throws IOException {
        // 定义源码的路径位置
        File sourceDirectory = new File(System.getProperty("user.dir"),"/deep-in-java/stage06/reflection-introduce/src/main/java");
        //System.out.println(System.getProperty("user.dir"));
        File targetDirectory = getClassOutputDirectory();
        // 基于Compiler 编译
        Compiler compiler = new Compiler(sourceDirectory,targetDirectory);
        // 设置注解处理器
        compiler.setProcessor(new RepositoryAnnotationProcessor());
        // 开始编译
        compiler.compile(
                "org.fufeng.java.reflection.User",
                "org.fufeng.java.reflection.Repository",
                "org.fufeng.java.reflection.CurdRepository",
                "org.fufeng.java.reflection.UserRepository");
    }

}
