package com.lcydream.project.jvm.deallock;

import java.util.concurrent.TimeUnit;

/**
 * @program: jguid
 * @description:
 *  jstack 工具演示 死锁  jstack -l PID 打印线程信息
 *      linux 转化成16进制: printf %x 10
 *      linux 转化成8进制: printf %o 10
 *      linux 转化成10进制: printf %d 10
 *  jmap -histo:live PID
 *  jmap -heap PID (JDK8，mac环境需要root执行sudo)
 *  jmap -dump:format=b,file=DealLockTest.dump PID
 *  -XX:+HeapDumpBeforeFullGC
 *  -XX:HeapDumpPath=/httx/logs/DealLockTest.hprof
 *  -XX:+HeapDumpOnOutOfMemoryError
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-15 21:25
 */
public class DealLockTest {

    private static final Object A = new Object();
    private static final Object B = new Object();

    public static void main(String[] args) {
        new Thread(DealLockTest::lockFunOne).start();
        new Thread(DealLockTest::lockFunTwo).start();
    }

    public static void lockFunOne(){
        synchronized (A){
            System.out.println("lockFunOne get A lock");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (B){

            }
        }
    }

    public static void lockFunTwo(){
        synchronized (B){
            System.out.println("lockFunTwo get B lock");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (A){

            }
        }
    }

}
