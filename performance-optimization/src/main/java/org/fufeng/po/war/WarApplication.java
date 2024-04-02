package org.fufeng.po.war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * TODO
 *
 * @author fufeng
 * @Date 2024-04-02 21:20
 */
@SpringBootApplication
public class WarApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WarApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WarApplication.class);
    }
}
