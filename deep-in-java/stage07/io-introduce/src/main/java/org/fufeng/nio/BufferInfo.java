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

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @program: jguid
 * @description: {@link Buffer}
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 * @see Buffer
 */
public class BufferInfo {

    public static void main(String[] args) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        // mark(-1) position(0) limit(8) capacity(8)
        byteBuffer.put((byte) 1); // mark(-1) position(1) limit(8) capacity(8)
        displayBufferMetaData(byteBuffer);
        // [1,x,x,x,x,x,x,x]
        //    p
        //                l
        //                c
        byteBuffer.rewind(); // 倒回去 p -> 回到0
        // [1,x,x,x,x,x,x,x]
        //  p
        System.out.println(byteBuffer.get());
        // [1,x,x,x,x,x,x,x]
        //    p
        displayBufferMetaData(byteBuffer);
    }

    protected static void displayBufferMetaData(Buffer buffer){
        System.out.printf("当前 buffer type:[ type : %s ] position=%d,limit=%d,capacity=%d\n",
                buffer.getClass().getName(),
                buffer.position(),
                buffer.limit(),
                buffer.capacity());
    }
}
