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
package org.fufeng.concurrent.cases.deallock.dealwith.one;

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
    public synchronized static boolean apply(Object... sources){
        // 如果有任何一个资源已经被申请了就返回false
        if (Arrays.stream(sources).anyMatch(alt::contains)){
            return false;
        }
        // 将已经被申请的资源注册到资源申请容器中
        alt.addAll(Arrays.asList(sources));
        return true;
    }

    /**
     *  释放所有已经申请到的资源
     * @param sources 资源列表
     */
    public synchronized static void free(Object... sources){
        alt.removeAll(Arrays.asList(sources));
    }

}
