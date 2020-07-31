/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-30
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.beans.properties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.fufeng.beans.properties.Person.isNumeric;

/**
 * @program: jguid
 * @description: {@link PropertyChangeEvent}
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-30
 * @see PropertyChangeEvent
 */
public class PersonInfo {

    public static void main(String[] args) throws PropertyVetoException {
        // person 示例
        Person person = new Person();
        // 添加PropertyChangeListener
        person.addPropertyChangeListener(event -> {
            // 属性变化通知事件
            System.out.printf("监听到属性[%s] 内容变化 (事件来源:%s),老值 %s,新值 %s\n",
                    event.getPropertyName(),
                    event.getSource(),
                    event.getOldValue(),
                    event.getNewValue());
        });

        // 添加PropertyVetoListener
        person.addVetoableChangeListener(event -> {
            String valueOfEvent = valueOf(event.getNewValue());
            // 判断事件是否是全数字
            if (isNumeric(valueOfEvent)){
                throw new PropertyVetoException(format("当前属性[%s]新值[%s],不能为纯数字!",
                        event.getPropertyName(),event.getNewValue()),event);
            }
        });

        // 模拟当前线程抛出异常
        Thread.setDefaultUncaughtExceptionHandler((t,e) -> e.printStackTrace());

        // 修改名称 null -> "magic"
        person.setName("magic");
        System.out.println("当前 person.name="+person.getName());

        // 修改名称 magic -> "fufeng"
        person.setName("fufeng");
        System.out.println("当前 person.name="+person.getName());

        // 修改名称 fufeng -> "1226"
        person.setName("1226");
        System.out.println("当前 person.name="+person.getName());
    }

}
