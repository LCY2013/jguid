/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-31
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.beans.persistence;

import org.fufeng.beans.properties.Person;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: person对象序列化特点
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-31
 */
public class PersonPersistenceInfo {

    public static void main(String[] args) throws Exception {
        // 定义Person类实例
        Person person = new Person();
        person.setName("fufeng");
        person.setAge(18);
        person.setId(7L);
        person.setUpdateTime(new Date());

        // 获取classPath路径
        String classPath = findClassPath();
        // 封装一个输出流
        OutputStream outputStream = new FileOutputStream(new File(classPath,"person.tmp"));
        // java标准序列化输出,将输出流包装在对象输出流里面
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        // 输出对象信息
        oos.writeObject(person);
    }

    private static String findClassPath() {
        // 获取系统环境变量中classPath的值
        String classPath = System.getProperty("java.class.path");
        // 开始筛选出符合条件的classPath
        return Stream.of(classPath.split(File.pathSeparator))
                .map(File::new)
                .filter(File::isDirectory)
                .filter(File::canRead)
                .filter(File::canWrite)
                .map(File::getAbsolutePath)
                .findFirst()
                .orElse(null);
    }

}
