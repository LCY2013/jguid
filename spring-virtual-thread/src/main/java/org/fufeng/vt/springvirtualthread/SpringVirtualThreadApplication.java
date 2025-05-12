package org.fufeng.vt.springvirtualthread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;

@SpringBootApplication
public class SpringVirtualThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringVirtualThreadApplication.class, args);
    }

    // 配置Tomcat使用虚拟线程执行器
    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
        return protocolHandler -> {
            protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        };
    }

    @RestController
    static class DemoController {

        // 传统阻塞IO端点
        @GetMapping("/blocking")
        public String blockingOperation() throws InterruptedException {
            // 模拟阻塞操作
            Thread.sleep(1000);
            return "Blocking operation handled by: " + Thread.currentThread();
        }

        // 虚拟线程优化端点
        @GetMapping("/virtual")
        public String virtualThreadOperation() throws InterruptedException {
            // 模拟阻塞操作
            //Thread.sleep(1000);
            //Thread.sleep(10);
            return "Virtual thread operation handled by: " + Thread.currentThread();
        }
    }

}
