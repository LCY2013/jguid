/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-27
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.memory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: jguid
 * @description: Synchronized 叙述
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-27
 */
public class SynchronizedWithRelationInfo {

    // 定义一个对象锁
    private final static Object lock = new Object();

    public static void main(String[] args) {
        // 测试没有volatile修饰的数据
        //volatileInfo();

        // 测试对象属性
        //initializeProperties();

        // 测试中断
        interruptThread();
    }

    /**
     *  测试volatile 关键字
     */
    private static void volatileInfo(){
        // 写线程
        new Thread(()->{
            try {
                // 先休眠300ms
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println("writeThread->"+notSafeData);
                // 开始写
                notSafeData = 2;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // 读线程
        new Thread(()->{
            // 先读取数据
            int data = notSafeData;
            System.out.println("readThread one->"+data);
            // 休眠300ms 等待写线程完成
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 再次读取shareData信息
            data = notSafeData;
            System.out.println("readThread two->"+data);
        }).start();
    }

    // 定义一个非volatile的数据
    private static int notSafeData;

    // 定义一个共享数据
    private static volatile int shareData;

    /**
     *  An unlock action on monitor m synchronizes-with all subsequent lock actions on
     *  m (where "subsequent" is defined according to the synchronization order)
     * @param data 锁数据
     */
    private static void synchronizedChangeData(int data){
        /*
            T1、T2、T3 线程同时获取锁
            T1先获取到锁 (lock -> run() -> unlock)
            T2、T3 进入停顿(park)
            T1 unlock
            T2 、 T3 获得竞争锁机会
            T3 获得锁 lock
            T2 进入停顿(park)
            T3 unlock
            T2 获得锁机会 lock
            T2 unlock
         */
        synchronized (lock){
            shareData = data;
        }
        // unlock the monitor(lock)
    }

    /**
     * A write to a volatile variable v (§8.3.1.4) synchronizes-with all subsequent
     * reads of v by any thread (where "subsequent" is defined according to the
     * synchronization order)
     * <p>
     * 假设
     * T(w) ：写线程
     * T(r) : 读线程
     * 且 T(w) 1 -> sharedData volatile 写 -> 0 => 1
     * T(r) 1...n -> sharedData volatile 读 -> 0 => 1
     *
     *  hardware :
     *      CPU 2-3 缓存
     *      主内存
     *      CPU缓存速度:主内存数度 = n:1
     *      产生了一个线程(CPU核)读取数据
     *          CPU <- 主内存
     *      当存在过个线程时，多个核对应的缓存行是不可见的，所以在线程中修改的变量也是不可见的，
     *      最后会写回主内存，这里就会造成变量的不可见，volatile就是实时把CPU多级缓存中的数据
     *      写回主内存，虽然是实时写回，但是也不可避免造成多个核读取到同一个数据然后同时写回主内存
     *      造成最终结果不一致，这就是volatile保证可见性，防止指令重排序，但是不能保证原子性的原因
     * @return shareData
     */
    private static int getShareData(){
        // volatile 读
        // shareData(1)
        return shareData;
    }

    private static void volatileChangeData(int data){
        // volatile 写
        // shareData(0) -> data
        shareData = data;

        // volatile 读
        int tmpData = shareData;
    }

    /**
     * An action that starts a thread synchronizes-with the first action in the thread it
     *  starts.
     */
    private static void threadStart(){
        Thread thread = new Thread(()->{});
        // start -> start0(native) 必然出现在run之前，run是一个普通方法，也就是一个方法句话
        thread.start();
    }

    private static class Person{
        // 定义姓名
        private final String name;
        // 定义年龄
        private final int age;
        // 定义标签
        private final Collection<String> tags;

        // 这里存在问题，如果类中存在成员常量那么字段就必须要在构造方法中初始化
//        public Person() {
//            this.name="";
//            this.age=18;
//            this.tags=null;
//        }

        /**
         *  在构造函数中，线程在读取类属性时，线程不会读取到字段初始化过程中的中间状态
         * @param name 名称
         * @param age 年龄
         * @param tags 标签
         */
        public Person(String name, int age, Collection<String> tags) {
            this.name = name;
            this.age = age;
            this.tags = tags;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", tags=" + tags +
                    '}';
        }
    }

    private static void initializeProperties(){
        // Person 对象初始化完成后,才能被其他线程访问对象属性
        List<String> tags = Arrays.asList("A","B","C");
        /*
            java 方法参数特点
            对于对象类型、引用
            引用: 普通对象、数组、集合(Collection、Map)
            对于原生类型: copy(值传递)
         */
        Person person = new Person("fufeng",18,tags);

        // 修改第一个值 A -> L
        tags.set(0,"L");

        Thread thread = new Thread(()-> System.out.println(person));
        thread.start();
    }

    // 线程中断
    private volatile boolean interrupted = false;

    private static void interruptThread(){
        Thread t1 = new Thread(()->{
            /*try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //  读取 t1 interrupt 值，true -> ClearInterrupted
            // The interrupted state is reset or not based on the value of ClearInterrupted that is passed.
            if (Thread.interrupted()){
                // 这里会执行
                System.out.println("t1 exe");
            }
        });

        Thread t2 = new Thread(()->{
            System.out.println("t2 开始中断t1");
            // t2 call t1.interrupt()
            t1.interrupt(); // volatile 写
            // t1 interrupt status false -> true
            System.out.println("t2 结束中断t1");
        });

        Thread t3 = new Thread(()->{
            /*try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            // volatile 读 t1 interrupt true
            if (t1.isInterrupted()){
                // 这里会执行
                System.out.println("t3 exe");
            }
        });

        // volatile 读 -> volatile 写
        // t2 -> interrupt t1 -> t1、t3 -> interrupted() read true

        t1.start();
        t2.start();
        t3.start();
    }
}
