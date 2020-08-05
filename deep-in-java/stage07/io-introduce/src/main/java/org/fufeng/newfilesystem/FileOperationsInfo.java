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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.fufeng.newfilesystem.PathInfo.USER_DIR;

/**
 * @program: jguid
 * @description: 文件操作相关
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 */
public class FileOperationsInfo {

    public static void main(String[] args) throws IOException {
        displayFileExists();
        displayFileAccessibility();
        displayFileEquals();
    }

    private static void displayFileEquals() throws IOException {
        final Path path = Paths.get(USER_DIR);
        final Path pathAnother = Paths.get(USER_DIR);
        System.out.println("path == pathAnother ? "+Files.isSameFile(path,pathAnother));
    }

    private static void displayFileAccessibility(){
        final Path path = Paths.get(USER_DIR);
        System.out.printf("${user.dir} : %s , readable : %s , writeable : %s , executeable : %s\n"
                ,path,Files.isReadable(path),Files.isWritable(path),Files.isExecutable(path));
    }

    private static void displayFileExists(){
        final Path path = Paths.get(USER_DIR);
        System.out.printf("${user.dir}:%s exists = %s\n",path, Files.exists(path));
    }

}
