package org.fufeng.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LuoCY
 *
 * jdk11 新增 jhsdb
 * 使用例子: jhsdb jmap --heap --pid
 */
@SpringBootApplication
public class JvmApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmApplication.class, args);
	}
}
