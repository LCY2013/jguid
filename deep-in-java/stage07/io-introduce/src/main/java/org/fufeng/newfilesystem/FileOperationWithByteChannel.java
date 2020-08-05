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

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.fufeng.newfilesystem.PathInfo.USER_DIR;

/**
 * @program: jguid
 * @description: 通过ByteBuffer操作文件
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 * @see ByteBuffer
 */
public class FileOperationWithByteChannel {

    public static void main(String[] args) throws IOException {
        // 设置UTF-8字符集编码
        final Charset charsetUtf8 = StandardCharsets.UTF_8;
        final Path pomXmlPath = Paths.get(USER_DIR,"pom.xml");
        final Path pomXmlCopyPath = Paths.get(USER_DIR, "pom-copy.xml");
        try (final SeekableByteChannel sourceSeekableByteChannel =
                     Files.newByteChannel(pomXmlPath);
             final SeekableByteChannel seekableCopyByteChannel =
                     Files.newByteChannel(pomXmlCopyPath,
                             StandardOpenOption.CREATE_NEW,StandardOpenOption.WRITE)){
            final ByteBuffer byteBuffer = ByteBuffer.allocate(16);
            while (sourceSeekableByteChannel.read(byteBuffer) > 0){
                //System.out.println(charsetUtf8.decode(byteBuffer));
                byteBuffer.rewind();
                seekableCopyByteChannel.write(byteBuffer);
                byteBuffer.flip();
                //byteBuffer.clear();
            }
        }
    }

}
