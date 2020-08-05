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
package org.fufeng.filesystem;

import java.io.FileDescriptor;
import java.lang.reflect.Field;

/**
 * @program: jguid
 * @description: 文件描述符 {@link FileDescriptor}
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 * @see FileDescriptor
 */
public class FileDescriptorInfo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        displayFileDescriptor(FileDescriptor.in);
        displayFileDescriptor(FileDescriptor.err);
        displayFileDescriptor(FileDescriptor.out);
    }

    /**
     *  展示文件描述符详情
     * @param fd 文件描述符
     * @throws NoSuchFieldException 异常
     * @throws IllegalAccessException 异常
     */
    private static void displayFileDescriptor(FileDescriptor fd) throws NoSuchFieldException, IllegalAccessException {
        final Integer fdInteger = getFieldType(fd, "fd");
        final Long handle = getFieldType(fd,"handle");
        final Boolean closed = getFieldType(fd,"closed");
        System.out.printf("FileDescriptor[fd : %s,handle : %s,closed : %s]\n",
                fdInteger,handle,closed);
    }

    /**
     *  通过文件描述符获取字段类型
     * @param fd 文件描述符
     * @param fieldName 字段名称
     * @param <T> 字段类型
     * @return 字段
     */
    private static <T> T getFieldType(FileDescriptor fd,String fieldName) throws NoSuchFieldException, IllegalAccessException {
        // 获取文件描述符字段
        final Field declaredField = FileDescriptor.class.getDeclaredField(fieldName);
        // 设置可见性
        declaredField.setAccessible(true);
        return (T) declaredField.get(fd);
    }

}
