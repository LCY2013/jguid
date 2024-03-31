package org.fufeng.po.cpu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * jstack cpu飙高或者线程数飙高、死锁排查步骤
 * 1、top -Hn 获取cpu使用率最高的进程
 * 2、jstack -l pid 获取进程的线程堆栈信息
 * 3、jstack -l pid | grep "at" 获取线程堆栈信息
 * 4、jstack -l pid | grep "waiting on" 获取线程等待锁的信息
 *
 * 流程如下：
 * 1、top -H -p pid 获取cpu使用率最高的进程
 * 2、printf "%x" $pid 获取进程pid十六进制
 * 3、jstack -l $pid 获取进程的线程堆栈信息，查看pid十六进制里面的线程信息
 *
 * @author fufeng
 * @Date 2024-03-31 20:59
 */
@SpringBootApplication
public class CpuApplication {

    public static void main(String[] args) {
        SpringApplication.run(CpuApplication.class, args);
    }

}
