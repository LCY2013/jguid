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
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @program: jguid
 * @description: {@link Path} 示例
 *      1、Fluent API(Builder API、Chain API)
 *      2、工具API(Objects)
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 * @see Path
 * @since 1.7
 */
public class PathInfo {

    /**
     *  当前项目所在目录
     */
    protected final static String USER_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        //displayPathInfo();
        //displayPathNormalize();
        displayPathConversation();
    }

    /**
     *  展示路径转换
     */
    private static void displayPathConversation(){
        final Path pathFromLocation = Paths.get(USER_DIR);
        final File file = new File(USER_DIR);
        final Path pathFromFile = file.toPath();
        final Path pathFromUri = Paths.get(pathFromFile.toUri());
        System.out.println("pathFromLocation : "+pathFromLocation);
        System.out.println("pathFromFile : "+pathFromFile);
        System.out.println("pathFromUri : "+pathFromUri);
        System.out.println("pathFromLocation == pathFromFile ? "+(pathFromFile.equals(pathFromLocation)));
        System.out.println("pathFromLocation == pathFromUri ? "+(pathFromUri.equals(pathFromLocation)));
    }

    /**
     *  支持操作系统的文件目录切换
     */
    private static void displayPathNormalize(){
        final Path path = Paths.get("/Users/magicLuoMacBook/software/java/ideawork/im/../");
        // /Users/magicLuoMacBook/software/java/ideawork/im/gitlab/
        // /Users/magicLuoMacBook/software/java/ideawork/im/../
        // /Users/magicLuoMacBook/software/java/ideawork/
        System.out.println(path.normalize());
    }

    /**
     *  展示path相关信息
     */
    private static void displayPathInfo(){
        final Path path = Paths.get(USER_DIR);
        System.out.printf("toString : %s\n",path);
        System.out.printf("getFileName : %s\n",path.getFileName());

        final int nameCount = path.getNameCount();
        for (int i=0;i<nameCount;i++){
            System.out.printf("getName(%d) : %s\n",i,path.getName(i));
        }
        for (Path p : path){
            System.out.printf("For-each path : %s\n",p);
        }
        System.out.printf("getParent : %s\n",path.getParent());
        System.out.printf("getRoot : %s\n",path.getRoot());
        FileSystems.getDefault().getRootDirectories().forEach(System.out::println);
    }
}
