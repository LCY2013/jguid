/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-12
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.autosave;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 自动保存任务
 * Balking 模式：
 *  如果现在不合适执行这个操作，或者没必要执行这个操作，就停止处理，直接返回。
 * 在Balking模式中，如果守护条件不成立，就立即中断处理。
 * <p>
 * Balking 模式本质上是一种规范化地解决“多线程版本的 if”的方案
 * @create 2020-11-12
 */
public class AutoSaveEditorV1 {

    /**
     * 是否改变
     */
    private boolean changed;

    /**
     * 模拟自动保存操作
     */
    private void save() {
        synchronized (this) {
            if (!changed) {
                return;
            }
            changed = false;
        }
        // 开始自动保存操作
        System.out.printf("[%s] 开始保存 ...\n", Thread.currentThread().getName());
    }

    /**
     * 是否已经改变
     */
    private void changed() {
        synchronized (this) {
            this.changed = true;
        }
    }

    /**
     * 模拟编辑的content内容
     */
    private void editor() {
        System.out.printf("[%s] 编辑后的内容\n", Thread.currentThread().getName());
        changed();
    }

    public static void main(String[] args) {
        final AutoSaveEditorV1 autoSaveEditor = new AutoSaveEditorV1();

        final ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleWithFixedDelay(autoSaveEditor::save, 0, 1, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(autoSaveEditor::editor, 0, 2, TimeUnit.SECONDS);
    }

}
