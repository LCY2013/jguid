/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-14
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.module.java.info;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Callable;

/**
 * @program: jguid
 * @description: 内部类模块化以后的变化细节
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-14
 */
public class InnerClassMain {

    //static 代码块
    static {
        new Runnable() {
            @Override
            public void run() {

            }
        };
    }

    //实例代码块
    {
        new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        };
    }

    //构造器
    public InnerClassMain() {
        new Comparable<Object>() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };
    }

    public static void main(String[] args) {
        //方法类实例
        final PropertyChangeListenerImpl propertyChangeListener =
                new PropertyChangeListenerImpl() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        super.propertyChange(evt);
                    }
                };
        propertyChangeListener.propertyChange(new PropertyChangeEvent(propertyChangeListener,"key","1","2"));
    }

    static class PropertyChangeListenerImpl implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            System.out.println(evt);
            new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    System.out.println(e);
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    System.out.println(e);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    System.out.println(e);
                }
            };
        }

    }

}
