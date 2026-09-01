package com.sri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SsoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoProjectApplication.class, args);
	}

}
