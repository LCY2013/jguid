package org.fufeng.po.btrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * btrace 使用(支持jdk17)
 * <a href="https://github.com/btraceio/btrace">btrace</a>
 *
 * btrace 使用后不会复原，注意使用
 *
 * @author fufeng
 * @Date 2024-03-31 20:59
 */
@SpringBootApplication
public class BtraceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BtraceApplication.class, args);
    }

}
