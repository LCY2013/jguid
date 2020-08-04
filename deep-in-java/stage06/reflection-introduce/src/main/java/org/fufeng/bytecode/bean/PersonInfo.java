/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-08-04
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.bytecode.bean;

import java.beans.PropertyVetoException;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.fufeng.bytecode.bean.Person.isNumeric;

/**
 * @program: jguid
 * @description: {@link Person} 测试示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-04
 * @see Person
 */
public class PersonInfo {

    public static void main(String[] args) {
        Person person = new Person();
        // 添加 PropertyChangeListener
        person.addPropertyChangeListener(event -> {
            // 属性变化通知事件
            System.out.printf("监听到属性[%s] 内容变化（事件来源：%s），老值：%s，新值：%s\n",
                    event.getPropertyName(),
                    event.getSource(),
                    event.getOldValue(),
                    event.getNewValue()
            );
        });

        // 添加 VetoableChangeListener
        person.addVetoableChangeListener(event -> {
            String newValue = valueOf(event.getNewValue());
            if (isNumeric(newValue)) {
                throw new PropertyVetoException(
                        format("当前属性[%s]的新值[%s]不合法，不能为纯数字!", event.getPropertyName(), newValue), event);
            }
        });


        // 修改名称 null -> "fufeng"
        person.setName("fufeng");
        System.out.println("当前 person.name = " + person.getName());

        // 修改名称 "fufeng" -> "magic"
        person.setName("magic");
        System.out.println("当前 person.name = " + person.getName());

        // 修改名称为 "magic" -> "12345"
        person.setName("12345");
        System.out.println("当前 person.name = " + person.getName());
    }

}
