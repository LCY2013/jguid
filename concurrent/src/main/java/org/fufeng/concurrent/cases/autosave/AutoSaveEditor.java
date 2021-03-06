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

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 自动保存任务
 * <p>
 * Balking 模式和 Guarded Suspension 模式从实现上看似乎没有多大的关系，
 * Balking 模式只需要用互斥锁就能解决，
 * 而 Guarded Suspension 模式则要用到管程这种高级的并发原语；
 * <p>
 * 从应用的角度来看，它们解决的都是“线程安全的 if”语义，
 * 不同之处在于，Guarded Suspension 模式会等待 if 条件为真，而 Balking 模式不会等待。
 * <p>
 * Balking 模式的经典实现是使用互斥锁，你可以使用 Java 语言内置 synchronized，也可以使用 SDK 提供 Lock；
 * 如果你对互斥锁的性能不满意，可以尝试采用 volatile 方案，不过使用 volatile 方案需要你更加谨慎。
 * @create 2020-11-12
 */
public class AutoSaveEditor {

    /**
     * 模拟一个随机数生成器
     */
    final Random random = new Random();

    /**
     * 模拟上次的内容
     */
    private int content;

    /**
     * 模拟自动保存操作
     */
    private void save() {
        final int editor = editor();
        if (editor != content) {
            System.out.printf("开始保存 [%d] ...\n", editor);
            this.content = editor;
        }
    }

    /**
     * 是否已经改变
     *
     * @return changed {@code true} unchanged{@code false}
     */
    private boolean isChanged() {
        return content == editor();
    }

    /**
     * 模拟编辑的content内容
     *
     * @return 编辑后的内容
     */
    private int editor() {
        final int content = random.nextInt(5);
        System.out.printf("本次编辑后的内容 %d\n", content);
        return content;
    }

    public static void main(String[] args) {

        final AutoSaveEditor autoSaveEditor = new AutoSaveEditor();

        final ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleWithFixedDelay(autoSaveEditor::save, 0, 1, TimeUnit.SECONDS);
    }

}
