package com.huoyun.idp;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackageClasses = { IdpApplication.class })
@EnableJpaRepositories
@SpringBootApplication
public class IdpApplication {

	public static void main(String[] args) {
		MDC.put("user", "jingjing");
		SpringApplication.run(IdpApplication.class, args);
	}
}
