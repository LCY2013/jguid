/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-08-05
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.newfilesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: 文件系统目录操作
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 */
public class DirectoryOperationsInfo {

    public static void main(String[] args) {
        String classPath = System.getProperty("java.class.path");
        Stream.of(classPath.split(File.separator))
                .map(Paths::get) // String -> Path
                .filter(Files::isDirectory)  // 是否是目录
                .filter(Files::isReadable)  // 可读
                .filter(Files::isWritable)  // 可写
                .map(Path::toString)  // Path -> String
                .map(dirPath -> Paths.get(dirPath,"parent-dir","sub-dir")) // String -> new Path
                .forEach(newDir -> {
                    try {
                        final Path directories = Files.createDirectories(newDir);
                        System.out.printf("新的目录[%s],已经被创建!",directories);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

}
