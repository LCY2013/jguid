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
package org.fufeng.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @program: jguid
 * @description: ObjectStream 使用
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-04
 * @see java.io.ObjectInputStream
 * @see java.io.ObjectOutputStream
 */
public class ObjectsStreamInfo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<String> strList = new ArrayList<String>(asList("A","B","C"));
        System.out.println(strList.hashCode());
        //new File(System.getProperty("user.dir"),"str.ser");
        File file = new File("str.ser");

        // 对象序列化
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(strList);
            oos.flush();
        }

        // 对象反序列化
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            final Object readObject = ois.readObject();
            System.out.println(readObject.hashCode());
            System.out.println(readObject);
        }
    }

}
