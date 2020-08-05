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

import java.io.File;
import java.util.Observable;

/**
 * @program: jguid
 * @description: 文件改变事件发布器
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 * @see java.util.Observer
 * @see FileChangeEvent
 * @see FileChangeListener
 */
public class FileChangeEventPublisher extends Observable {

    /**
     * 文件作为发布源
     *
     * @param file 文件
     */
    public void publish(File file) {
        publish(new FileChangeEvent(file));
    }

    /**
     * 发布 {@link FileChangeEvent} 事件
     *
     * @param changeEvent 文件改变事件
     */
    public void publish(FileChangeEvent changeEvent) {
        // 设置监听器状态
        this.setChanged();
        super.notifyObservers(changeEvent);
    }

    /**
     * 添加文件改变事件监听器
     *
     * @param fileChangeListener 文件改变监听器
     */
    public void addFileChangeListener(FileChangeListener fileChangeListener) {
        addObserver(fileChangeListener);
    }

    public static void main(String[] args) {
        // 创建一个文件改变事件发布器
        FileChangeEventPublisher publisher = new FileChangeEventPublisher();
        // 添加监视器
        publisher.addFileChangeListener(fileChangeEvent ->
                System.out.printf("监听到事件[%s]\n",fileChangeEvent));
        // 发布文件改变事件
        publisher.publish(new File(".idea"));
    }

}
