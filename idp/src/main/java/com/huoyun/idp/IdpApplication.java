package com.huoyun.idp;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdpApplication {

	public static void main(String[] args) {
		MDC.put("user", "jingjing");
		SpringApplication.run(IdpApplication.class, args);
	}
}
