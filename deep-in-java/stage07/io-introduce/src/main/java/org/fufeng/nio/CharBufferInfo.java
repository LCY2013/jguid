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

import java.nio.CharBuffer;

import static org.fufeng.nio.BufferInfo.displayBufferMetaData;

/**
 * @program: jguid
 * @description: {@link CharBuffer} 字符buffer使用
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 * @see java.nio.CharBuffer
 */
public class CharBufferInfo {

    public static void main(String[] args) {
        // [X,X,X,X,X,X,X,X]
        //  p
        final CharBuffer charBuffer = CharBuffer.allocate(8);
        // 文本内容小于容量
        String message = "fufeng";
        // 内容为fufeng
        // [f,u,f,e,n,g,X,X]
        //              p
        //                l
        //                c
        // flip() -> limit = position ; position = 0 ; mark = -1
        // [f,u,f,e,n,g,X,X]
        //  p
        //              l
        //                c
        // rewind() -> position = 0; mark = -1
        // [f,u,f,e,n,g,X,X]
        //  p
        //                l
        //                c
        for (char c : message.toCharArray()){
            charBuffer.put(c);
            // 若执行 flip() 操作
            // rewind()
            // p = 0, limit(8) 循环
        }
        // 展示现在的buffer内容
        displayBufferMetaData(charBuffer);

        // 如果执行flip()
        // limit -> 6
        // position -> 0
        // [f,u,f,e,n,g,X,X]
        //  p
        //              l
        //                c
        charBuffer.flip();
        displayBufferMetaData(charBuffer);

        // 依次读取buffer内有效元素
        while (charBuffer.hasRemaining()){  // 循环6次
            System.out.print(charBuffer.get());
        }

        System.out.println();

        displayBufferMetaData(charBuffer);
    }

}
