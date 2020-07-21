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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @program: jguid
 * @description: {@link Collection} 空集合使用示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-21
 */
public class EmptyCollectionInfo {

    public static void main(String[] args) {

        // 对自己（严格）：所有的返回接口类型的方法禁止返回 null
        // 对别人（宽容）：要做 null 判断（尤其在 RPC 场景）

        // 集合方法入参：
        // 1. 如果能用 Iterable 尽量用
        // 2. 其次是 Collection
        // 3. 再者是 List 或 Set
        // 禁止使用具体类型，比如：ArrayList，LinkedHashSet
    }


    public static List<String> getIdsList(String name) {
        if (name == null || name.length() < 1) {
            return Collections.emptyList();
        }
        // 只读 empty List
        // 实现 Java 序列化
        return Collections.emptyList();
    }
}
