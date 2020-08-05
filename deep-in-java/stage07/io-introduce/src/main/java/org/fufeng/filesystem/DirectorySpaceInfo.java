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
import java.io.FileFilter;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description:
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-04
 */
public class DirectorySpaceInfo {

    // 文件 -> File.length
    // 目录 -> file.listFiles
    private final File rootDirectory;

    private final Predicate<File> filter;

    public DirectorySpaceInfo(File rootDirectory, FileFilter... filter) {
        this.rootDirectory = rootDirectory;
        this.filter = new FilePredicate(filter);
    }

    private interface FilePredicateAdaptor extends Predicate<File>, FileFilter{
        @Override
        default boolean accept(File pathname){
            return test(pathname);
        }

        /*@Override
        boolean accept(File pathname);*/
    }

    private class FilePredicate implements Predicate<File>{

        private final FileFilter[] fileFilters;

        public FilePredicate(FileFilter[] fileFilters) {
            this.fileFilters = fileFilters;
        }

        @Override
        public boolean test(File file) {
            for (FileFilter fileFilter : this.fileFilters){
                if (!fileFilter.accept(file)){
                    return false;
                }
            }
            return true;
        }
    }

    public long getSpace(){
        FileFilter fileFilter = null;
        if (this.rootDirectory.isFile()){
            return this.rootDirectory.length();
        } else if (this.rootDirectory.isDirectory()){
            final File[] subListFiles = this.rootDirectory.listFiles();
            long space = 0L;
            // 累加当前目录的文件
            space += Stream.of(Objects.requireNonNull(subListFiles))
                    .filter(File::isFile)
                    .filter(filter)
                    .map(File::length)
                    .reduce(Long::sum)
                    .orElse(0L);

            // 递归其子目录
            space += Stream.of(subListFiles)
                    .filter(File::isDirectory)
                    .filter(filter)
                    .map(DirectorySpaceInfo::new)
                    .map(DirectorySpaceInfo::getSpace)
                    .reduce(Long::sum)
                    .orElse(0L);
            return space;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new DirectorySpaceInfo(new File(System.getProperty("user.dir")),
                file -> file.getName().endsWith(".idea")).getSpace() / 1024);
    }
}
