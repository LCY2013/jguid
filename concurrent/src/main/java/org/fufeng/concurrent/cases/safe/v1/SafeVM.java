/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-11
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.concurrent.cases.safe.v1;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 可见性、原子性
 * @create 2020-11-11
 */
public class SafeVM {

    /**
     * 初始化一个原子类型引用
     */
    private final AtomicReference<VmRange> vmRangeAtomicReference =
            new AtomicReference<>(new VmRange(0, 0));

    /**
     * 设置上边界
     *
     * @param upper 上边界值
     */
    private void setUpper(int upper) {
        do {
            final VmRange vmRange = vmRangeAtomicReference.get();
            if (upper < vmRange.getLower()) {
                throw new IllegalArgumentException("upper must grate than lower");
            }
            final VmRange newVmRange = new VmRange(upper,vmRange.getLower());
            vmRangeAtomicReference.compareAndSet(vmRange,newVmRange);
        } while (true);
    }
}
