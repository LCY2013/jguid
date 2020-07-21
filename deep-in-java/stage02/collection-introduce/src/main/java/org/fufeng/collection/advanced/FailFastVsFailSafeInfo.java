/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-21
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.collection.advanced;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: jguid
 * @description: 快速失败 与 安全失败 比较
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-21
 */
public class FailFastVsFailSafeInfo {

    public static void main(String[] args) {
        // 关于快速失败
        infoFailFast();
        // 关于安全失败
        infoFailSafe();
    }

    /**
     *  安全失败测试
     */
    private static void infoFailSafe() {
        removeForEach(new ArrayList<>(List.of(1,2,3,4,5)));
    }

    /**
     *  快速失败测试
     */
    private static void infoFailFast() {
        removeForEach(new CopyOnWriteArrayList<>(List.of(6,7,8,9,0)));
    }

    /**
     *  通过集合对象进行删除操作
     * @param values 集合对象
     */
    private static void removeForEach(Collection<?> values){
        try {
            // fast fail 是一种快速失败机制, 比如集合中的ArrayList,产生ConcurrentModification
            // safe fail 是一种安全失败机制, 比如CopyOnWriteList,不会对结果产生影响
            for (Object obj : values){
                values.remove(obj);
            }
            System.out.println("集合元素删除成功,目前集合空间大小:"+values.size());
        }catch (ConcurrentModificationException e){
            System.err.println("快速失败:"+e.getMessage());
        }
    }

}
