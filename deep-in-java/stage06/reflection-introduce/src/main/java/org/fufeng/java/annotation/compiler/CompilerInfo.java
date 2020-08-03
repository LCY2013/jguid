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

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 * @program: jguid
 * @description: {@link Compiler} 源文件编辑器
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-03
 * @see Compiler
 */
public class CompilerInfo {

    public static void main(String[] args) throws IOException {
        // 获取JavaCompiler
        final JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        // 获取一个标准的文件管理器
        final StandardJavaFileManager standardFileManager =
                javaCompiler.getStandardFileManager(null, null, null);
        // 指定java Source路径 javac -sourcePath
        // 目标类 org.fufeng.java.annotation.compiler.CompilerInfo
        Class<?> targetClass = CompilerInfo.class;
        // 目标类相对路径获取 org/fufeng/java/annotation/compiler/CompilerInfo.java
        String sourceFileRelativePath = targetClass.getName().replace(".",File.separator).concat(".java");
        // 目标类的源文件 (Maven 路径) : {$user.dir}/src/main/java + 目标类的源文件 (相对路径)
        String sourceFilePath = System.getProperty("user.dir") + "/deep-in-java/stage06/reflection-introduce/src/main/java/" + sourceFileRelativePath;
        File sourceFile = new File(sourceFilePath);
        // 指定Java新生成的Classes输出目录,并非编译时依赖的classPath java -d
        standardFileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singleton(getClassOutputDirectory()));

        final Iterable<? extends JavaFileObject> compilationUnits =
                standardFileManager.getJavaFileObjectsFromFiles(asList(sourceFile));
        final JavaCompiler.CompilationTask task = javaCompiler.getTask(new OutputStreamWriter(System.out),
                standardFileManager, null, null, null, compilationUnits);
        // 执行编译
        task.call();
    }

    /**
     *  获取字节码输出的文件夹
     * @return 返回字节码输出文件夹
     */
    public static File getClassOutputDirectory(){
        // 查询当前类路径的目录
        final File concurrentClassPath = new File(findClassPath());
        // 获取当前类路径的父目录
        final File classPathParentFile = concurrentClassPath.getParentFile();
        // 定义编译后的字节码存储路径
        File classesPath = new File(classPathParentFile,"classes-info");
        // 判断该目录是否存在,不存在就创建
        if (!classesPath.exists()){
            classesPath.mkdirs();
        }
        return classesPath;
    }

    /**
     *  查询当前类路径
     * @return 类路径字符串
     */
    public static String findClassPath(){
        final String classPath = System.getProperty("java.class.path");
        return Stream.of(classPath.split(File.separator))
                .map(File::new)
                .filter(File::isDirectory)
                .filter(File::canWrite)
                .filter(File::canRead)
                .map(File::getAbsolutePath)
                .findFirst()
                .orElse(null);
    }
}
