package org.fufeng.po.memory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * memory
 * 1、内存溢出自动导出
 *  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./heapdump.hprof
 * 2、使用jmap命令手动导出
 *  jmap -dump:format=b,file=heapdump.hprof PID
 *
 * @author fufeng
 * @Date 2024-03-31 20:59
 */
@SpringBootApplication
public class MemoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoryApplication.class, args);
    }

}
