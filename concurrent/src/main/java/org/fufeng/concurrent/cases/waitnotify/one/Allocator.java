/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-10-29
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.waitnotify.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 同时申请所有锁资源
 * @create 2020-10-29
 */
public class Allocator {

    // 该资源申请器的容器
    private final static List<Object> alt = new ArrayList<>();

    /**
     *  同时申请所有的资源
     * @param sources 所有资源
     * @return 是否申请成功
     */
    public synchronized void apply(Object... sources){
        // 如果有任何一个资源已经被申请了就返回false
        if (Arrays.stream(sources).anyMatch(alt::contains)){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 将已经被申请的资源注册到资源申请容器中
        alt.addAll(Arrays.asList(sources));
    }

    /**
     *  释放所有已经申请到的资源
     * @param sources 资源列表
     */
    public synchronized void free(Object... sources){
        alt.removeAll(Arrays.asList(sources));
        // 尽量使用notifyAll，而不是notify，notify可能会造成线程永远不会被唤醒
        /*
                    wait
           活跃      -->       等待区
               |\            /
     争取到资源  |           |/  notify
                  临界区

            T1 持有 A 资源   活跃
            T2 持有 B 资源   活跃

            T3 需要 A 资源   等待
            T4 需要 B 资源   等待

            T1 释放A资源，当使用notify通知的时候只会通知一个线程进入临界区，这个时候可能通知到T4，但是T4需要的是B资源，T4继续进行等待，T3这个时候就没有进入临界区被唤醒的机会了
         */
        notifyAll();
    }

}
