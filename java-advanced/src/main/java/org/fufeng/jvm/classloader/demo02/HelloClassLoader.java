/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2021-03-07
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.jvm.classloader.demo02;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 通过类加载器加载某个二进制的字节码编码后的文件，转换后加载进jvm，并调用其执行 hello 方法
 * @create 2021-03-07
 * @since 1.0
 */
public class HelloClassLoader extends ClassLoader {

    private static final String ClassLoaderPath = "Hello.encodeclass";

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        final Class<?> hello = helloClassLoader.findClass("Hello");
        final Method helloMethod = hello.getMethod("hello");
        helloMethod.invoke(hello.newInstance());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream inputStream = this.getClass().getResourceAsStream(ClassLoaderPath);
        if (Objects.isNull(inputStream)){
            throw new RuntimeException("待加载文件未找到");
        }
        // 解码读取后的字节码信息
        byte[] bytes = null;
        try {
            bytes = decodeClassBytecode(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, bytes, 0, bytes.length);
    }


    private byte[] decodeClassBytecode(InputStream inputStream) throws IOException {
        // 定义一个buffer
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int length;
        while ((length = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes,0,length);
        }
        final byte[] bytesEncode = byteArrayOutputStream.toByteArray();
        for (int i = 0; i < bytesEncode.length; i++) {
            bytesEncode[i] = (byte) (255 - bytesEncode[i]);
        }
        return bytesEncode;
    }
}
