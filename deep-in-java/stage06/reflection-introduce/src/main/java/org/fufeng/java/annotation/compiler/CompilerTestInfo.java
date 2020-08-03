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
package org.fufeng.java.annotation.compiler;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: 编译测试
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-03
 */
public class CompilerTestInfo {

    public static void main(String[] args) throws IOException {
        File sourceDirectory = new File(System.getProperty("user.dir"), "/src/main/java/");
        File targetDirectory = getClassOutputDirectory();
        // 基于 Compiler
        Compiler compiler = new Compiler(sourceDirectory, targetDirectory);
        compiler.compile(CompilerTestInfo.class.getName());
    }

    private static File getClassOutputDirectory(){
        final File concurrentClassPath = new File(findClassPath());
        final File parentFile = concurrentClassPath.getParentFile();
        File classOutputDirectory = new File(parentFile,"classes-info");
        // 生成字节码输出目录
        if (!classOutputDirectory.exists()){
            classOutputDirectory.mkdir();
        }
        return classOutputDirectory;
    }

    private static String findClassPath(){
        String classPath = System.getProperty("java.class.path");
        return Stream.of(classPath.split(File.separator))
                .map(File::new)
                .filter(File::isDirectory)
                .filter(File::canRead)
                .filter(File::canWrite)
                .map(File::getAbsolutePath)
                .findFirst()
                .orElse(null);
    }
}
