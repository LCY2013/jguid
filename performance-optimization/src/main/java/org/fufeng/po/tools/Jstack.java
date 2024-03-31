package org.fufeng.po.tools;

/**
 * jstack 命令 获取线程信息
 * jstack -l pid 获取线程堆栈信息
 * <p>
 *  线程状态
 *  1、NEW
 *  2、RUNNABLE
 *  3、BLOCKED
 *  4、WAITING
 *  5、TIMED_WAITING
 *  6、TERMINATED
 *  线程堆栈信息
 *  1、线程名称
 *  2、线程状态
 *  3、线程堆栈信息
 *  4、线程ID
 *  5、线程优先级
 *  6、线程所属线程组
 *  7、线程是否守护线程
 *  8、线程是否中断
 * <p/>
 *
 * @author fufeng
 * @Date 2024-03-31 21:46
 */
public class Jstack {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
