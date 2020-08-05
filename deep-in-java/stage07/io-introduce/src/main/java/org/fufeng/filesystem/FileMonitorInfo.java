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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: 文件改变监视器
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-05
 */
public class FileMonitorInfo {

    /**
     *  key -> 被监视文件对象
     */
    private final Map<File,Long> lastModifiedSnapshot = new LinkedHashMap<>();

    /**
     *  轮训监控文件改变执行器
     */
    private final ScheduledExecutorService pollingWatchingService = Executors.newScheduledThreadPool(1);

    /**
     *  文件改变事件发布器
     */
    private final FileChangeEventPublisher publisher = new FileChangeEventPublisher();

    /**
     *  监控文件变化
     * @param monitoredFile 监控文件
     */
    public void monitor(File monitoredFile){
        updateLastModifiedSnapshot(monitoredFile);
        // 判断是否发送改变
        pollingWatchingService.scheduleAtFixedRate(()->{
            final long concurrentLastModified = monitoredFile.lastModified();
            final Long previousLastModified = this.lastModifiedSnapshot.
                    putIfAbsent(monitoredFile, monitoredFile.lastModified());
            if (concurrentLastModified > previousLastModified){
                this.publisher.publish(monitoredFile);
                updateLastModifiedSnapshot(monitoredFile);
            }
        },0L,5L, TimeUnit.SECONDS);
    }

    /**
     *  更新上次文件修改的信息
     * @param monitoredFile 监控的文件
     */
    private void updateLastModifiedSnapshot(File monitoredFile){
        lastModifiedSnapshot.put(monitoredFile,monitoredFile.lastModified());
    }

    /**
     *  添加文件改变监听事件
     * @param listener 监听器
     * @param listeners 其他监听器
     */
    private void addFileChangeListeners(FileChangeListener listener,FileChangeListener... listeners){
        this.publisher.addFileChangeListener(listener);
        Stream.of(listeners).forEach(this.publisher::addFileChangeListener);
    }

    public static void main(String[] args) {
        FileMonitorInfo fileMonitor = new FileMonitorInfo();
        fileMonitor.addFileChangeListeners(fileChangeEvent -> System.out.println("文件变化事件:"+fileChangeEvent));
        fileMonitor.monitor(new File("/Users/magicLuoMacBook/software/java/ideawork/im/gitlab/jguid/deep-in-java/stage07/io-introduce/src/main/java/org/fufeng/filesystem/FileMonitorInfo.java"));
    }

}
