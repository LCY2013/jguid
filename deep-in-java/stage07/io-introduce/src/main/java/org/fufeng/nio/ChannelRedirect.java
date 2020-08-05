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
package org.fufeng.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @program: jguid
 * @description: {@link Channel}
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 */
public class ChannelRedirect {

    public static void main(String[] args) throws IOException {
        // System.in 和 System.out
        // InputStream 和 OutputStream
        // copy(System.in,System.out);
        final ReadableByteChannel readableByteChannel = Channels.newChannel(System.in);
        final WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
        copy(readableByteChannel,writableByteChannel);
    }

    private static void copy(ReadableByteChannel readableByteChannel, WritableByteChannel writableByteChannel) throws IOException {
        // 申请4k大小的堆内存
        final ByteBuffer byteBuffer = ByteBuffer.allocate(4 * 1024);
        while (readableByteChannel.read(byteBuffer) != -1){
            // 从 写模式 -> 读模式
            byteBuffer.flip();
            // 判断是否可读
            if (byteBuffer.hasRemaining()){
                writableByteChannel.write(byteBuffer);
            }
            byteBuffer.clear();
        }
    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        // 定义一个4k的堆内存信息
        byte[] buffer = new byte[4*1024];
        int readLength = -1;
        while ((readLength = inputStream.read(buffer)) != -1){
            outputStream.write(buffer,0,readLength);
        }
    }
}
