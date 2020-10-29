package com.lcydream.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JguidApplication {

    public static void main(String[] args) {
        SpringApplication.run(JguidApplication.class, args);
    }

    @GetMapping("/get/{username}")
    public String getName(@PathVariable String username) {
        return username + ":" + System.currentTimeMillis();
    }

}
