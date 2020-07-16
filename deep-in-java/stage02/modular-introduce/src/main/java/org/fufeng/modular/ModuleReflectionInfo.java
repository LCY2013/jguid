/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-16
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.modular;

import java.lang.module.ModuleDescriptor;

/**
 * @program: jguid
 * @description: {@link Module} 模块化示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-16
 */
public class ModuleReflectionInfo {

    public static void main(String[] args) {
        // 获取ModuleReflectionInfo 字节码对象
        Class<ModuleReflectionInfo> clazz =
                ModuleReflectionInfo.class;
        // 获取Module对象
        final Module module = clazz.getModule();
        System.out.printf("类 %s 存在于模块 - %s \n",clazz.getName(),module.getName());

        // 获取模块描述信息
        final ModuleDescriptor descriptor = module.getDescriptor();
        descriptor.requires()
                .forEach(requires ->
                        System.out.printf("requires 模块名称: %s,修饰符定义: %s \n",
                                requires.name(),requires.modifiers()));
        descriptor.exports().forEach(exports ->
                System.out.printf("exports 包名称 %s, target : %s \n",
                        exports.source(),exports.targets()));
    }

}
