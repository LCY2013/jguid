/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-12-21
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.project.base.thread.threadpoolsize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Vector;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description IO 型计算
 * 如果测试代码读取 相对较大 的文件，涉及到大内存，所以在运行之前，需要调整 JVM 的堆内存空间：-Xms4g -Xmx4g
 * @create 2020-12-21
 */
public class IOType implements Runnable {


    //整体执行时间，包括在队列中等待的时间
    Vector<Long> wholeTimeList;
    //真正执行时间
    Vector<Long> runTimeList;

    private long initStartTime = 0;

    /**
     * 构造函数
     *
     * @param runTimeList
     * @param wholeTimeList
     */
    public IOType(Vector<Long> runTimeList, Vector<Long> wholeTimeList) {
        initStartTime = System.currentTimeMillis();
        this.runTimeList = runTimeList;
        this.wholeTimeList = wholeTimeList;
    }

    /**
     * IO操作
     *
     * @return
     * @throws IOException
     */
    public void readAndWrite() throws IOException {
        final InputStream resourceAsStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("application.properties");
        //File sourceFile = new File("application.properties");
        //创建输入流
        //BufferedReader input = new BufferedReader(new FileReader(sourceFile));
        assert resourceAsStream != null;
        BufferedReader input = new BufferedReader(new InputStreamReader(resourceAsStream));
        //读取源文件,写入到新的文件
        String line;
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        //关闭输入输出流
        input.close();
    }

    public void run() {
        long start = System.currentTimeMillis();
        try {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            readAndWrite();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        long wholeTime = end - initStartTime;
        long runTime = end - start;
        wholeTimeList.add(wholeTime);
        runTimeList.add(runTime);
        System.out.println("单个线程花费时间：" + (end - start));
    }

    public static void main(String[] args) throws IOException {
        final InputStream resourceAsStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("image/application.properties");
        if (Objects.nonNull(resourceAsStream)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(resourceAsStream));
            //读取源文件,写入到新的文件
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            resourceAsStream.close();
        }
    }

}