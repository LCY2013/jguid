/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-12-07
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.project.base.serializable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 比较java对象序列化和ByteBuffer 参数的对象数据大小
 * @create 2020-12-07
 */
public class ObjectOutputStreamVsByteBuffer {

    public static void main(String[] args) throws IOException {
        // user 对象初始化
        final User user = new User();
        user.setUserName("fufeng");
        user.setPassword("123456");

        serializedByteLength(user);

        serializedSpendTime(user);
    }

    private static void serializedSpendTime(User user) throws IOException {
        final long objectOutputStreamStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            // 通过java对象序列化初始化
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(user);
            oos.flush();
            oos.close();
            final byte[] oosBytes = baos.toByteArray();
            baos.close();
        }
        System.out.printf("ObjectOutputStream spend time : %d \n", System.currentTimeMillis() - objectOutputStreamStartTime);

        final long byteBufferStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            // 通过ByteBuffer 写对象数据
            final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 写字段
            final byte[] userNameBytes = user.getUserName().getBytes();
            byteBuffer.putInt(userNameBytes.length);
            byteBuffer.put(userNameBytes);

            final byte[] passwordBytes = user.getPassword().getBytes();
            byteBuffer.putInt(passwordBytes.length);
            byteBuffer.put(passwordBytes);
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
        }
        System.out.printf("ByteBuffer spend time : %d \n", System.currentTimeMillis() - byteBufferStartTime);
    }

    private static void serializedByteLength(User user) throws IOException {
        // 通过java对象序列化初始化
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(user);

        final byte[] oosBytes = baos.toByteArray();
        System.out.printf("ObjectOutputStream 字节码长度: %d \n", oosBytes.length);

        // 通过ByteBuffer 写对象数据
        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 写字段
        final byte[] userNameBytes = user.getUserName().getBytes();
        byteBuffer.putInt(userNameBytes.length);
        byteBuffer.put(userNameBytes);

        final byte[] passwordBytes = user.getPassword().getBytes();
        byteBuffer.putInt(passwordBytes.length);
        byteBuffer.put(passwordBytes);
        byteBuffer.flip();
        System.out.printf("ByteBuffer 字节长度: %d \n", byteBuffer.remaining());
    }

    private static class User implements Serializable {

        private User singleton = new User();

        private static final long serialVersionUID = 3403450892906866410L;

        private String userName;

        private String password;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        /**
         * 单例被破坏的问题是java对象反序列化过程中readObject方法被调用，会反射调用单例对象的构造函数
         * 可以通过这个方法防止反序列化过程中对单例的破坏
         *
         * @return 单例对象
         */
        private Object readResolve() {
            // 返回单例对象
            return singleton;
        }
    }

}
