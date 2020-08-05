/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-08-04
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.filesystem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.String.valueOf;

/**
 * @program: jguid
 * @description: 模拟文件系统的dir命令
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-04
 */
public class DirCommandInfo {

    // 需要展示的顶级目录
    private final File rootDirectory;

    public DirCommandInfo(File rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public void execute(){
        // 通过流式表达计算出下面的列表
        Stream.of(Objects.requireNonNull(rootDirectory.listFiles()))
                .map(file -> {
                    // 通过StringBuilder构建结果的目录集合
                    StringBuilder stringBuilder = new StringBuilder();
                    // Date + time
                    final long lastModified = file.lastModified();
                    // 判断是否是文件夹
                    final String fileType = file.isDirectory()?"d":file.isFile()?"-":"l";
                    final String fileLength = valueOf(file.length());
                    final String fileName = file.getName();
                    final SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    final String updateTime = simpleFormatter.format(new Date(lastModified));
                    return stringBuilder.append(fileType).append("\t\t\t")
                            .append(fileLength).append("\t\t\t")
                            .append(fileName).append("\t\t\t")
                            .append(updateTime)
                            .toString();
                }).forEach(System.out::println);
    }

    public static void main(String[] args) {
        new DirCommandInfo(new File(System.getProperty("user.dir"))).execute();
    }

}
