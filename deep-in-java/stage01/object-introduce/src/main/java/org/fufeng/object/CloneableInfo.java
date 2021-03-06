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
package org.fufeng.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: jguid
 * @description: {@link Cloneable} java基础cloneable示例
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-14
 */
public class CloneableInfo {

    public static void main(String[] args) throws CloneNotSupportedException {
        //使用Data对象
        Data data = new Data();
        data.setId(1);
        data.setName("fufeng");
        data.setList(Arrays.asList(1,2,3,5,6));
        final Data copy = data.clone();
        System.out.println("data == copy ? " + (data == copy));
        System.out.println("data.getName() == copy.getName() ? " + (data.getName() == copy.getName()));
        System.out.println("data.getName() == copy.getName() ? " + (data.getName().equals(copy.getName())));
        System.out.println("data.getId() == copy.getId() ? " + (data.getId() == copy.getId()));
        System.out.println("data.getList() == copy.getList() ? " + (data.getList() == copy.getList()));
        System.out.println(data);
        System.out.println(copy);
    }

    static class Data implements Serializable,Cloneable{
        private int id;
        private String name;
        private List<Object> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Object> getList() {
            return list;
        }

        public void setList(List<Object> list) {
            this.list = list;
        }

        /**
         * 重写clone方法，覆盖Object中clone修饰符权限为protected不足的问题
         * @return 返回克隆的Data数据对象
         * @throws CloneNotSupportedException 该对象是否满足clone的条件，就是是否实现clone接口
         */
        @Override
        protected Data clone() throws CloneNotSupportedException {
            //对象的基本数据类型不存在浅拷贝问题，因为都是值传递
            //而对于引用类型，引用传递也是指向同一个内存空间，所以需要重新创建
            Data data = (Data)super.clone();
            data.setName(new String(this.name));
            data.setList(new ArrayList<>(this.list));
            return data;
        }

//        @Override
//        public int hashCode() {
//            return this.id;
//        }


//        @Override
//        public String toString() {
//            return "Data{" +
//                    "id=" + id +
//                    ", name='" + name + '\'' +
//                    ", list=" + list +
//                    '}';
//        }
    }
}
