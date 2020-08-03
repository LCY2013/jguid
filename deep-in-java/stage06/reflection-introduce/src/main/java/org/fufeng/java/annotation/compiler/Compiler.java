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

import javax.annotation.processing.Processor;
import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * @program: jguid
 * @description: 自定义编译器
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-03
 * @see Compiler
 */
public class Compiler {

    // 定义源文件路径,指定 Java source文件的跟目录 -sourcepath
    private final File sourceDirectory;
    // 定义字节码路径
    private final File classDirectory;
    // 定义注解解析工具
    private Iterable<? extends Processor> processor;
    // 定义Java提供的编辑器
    private final JavaCompiler javaCompiler;
    // 定义标准的文件管理器
    private final StandardJavaFileManager fileManager;

    /**
     *  设置需要编译的源码目录和输出的字节码目录
     * @param sourceDirectory 源码目录
     * @param classDirectory 字节码目录
     */
    public Compiler(File sourceDirectory,File classDirectory) {
        this.sourceDirectory = sourceDirectory;
        this.classDirectory = classDirectory;
        this.javaCompiler = ToolProvider.getSystemJavaCompiler();
        this.fileManager = this.javaCompiler.getStandardFileManager(null,null,null);
    }

    /**
     *  设置注解处理器
     * @param processor 注解处理器
     */
    public void setProcessor(Processor... processor) {
        this.processor = asList(processor);
    }

    /**
     *  需要编译的类全路径名称
     * @param classNames 类全路径名集合,类所在目录相对于 {@link #sourceDirectory}
     */
    public void compile(String... classNames) throws IOException {
        // 指定 Java 新生成的class输出目录 (并非编译时依赖的ClassPath) javac -d
        this.fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singleton(classDirectory));
        // 获取指定包路径下的所有源文件
        final List<File> sourceFiles = Stream.of(classNames)
                .map(name -> name.replace(".", File.separator).concat(".java"))  // 通过类的全路径名称获取某个os中的物理路径格式
                .map(name -> sourceDirectory.getAbsolutePath() + File.separator + name) // 获取绝对的物理路径然后定义源文件
                .map(File::new)
                .collect(toList());
        // 获取java文件队列
        final Iterable<? extends JavaFileObject> compilationUnits =
                this.fileManager.getJavaFileObjectsFromFiles(sourceFiles);
        // 开始编译源文件
        final JavaCompiler.CompilationTask compilationTask = this.javaCompiler.getTask(new OutputStreamWriter(System.out),
                fileManager, null, null, null, compilationUnits);
        // 设置Processor
        compilationTask.setProcessors(this.processor);
        // 执行编译
        compilationTask.call();
    }

    /**
     *  获取源文件目录
     * @return 文件目录
     */
    public File getSourceDirectory() {
        return sourceDirectory;
    }

    /**
     *  获取字节码目录
     * @return 字节码目录
     */
    public File getClassDirectory() {
        return classDirectory;
    }
}
