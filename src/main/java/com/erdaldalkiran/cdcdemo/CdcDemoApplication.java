package com.erdaldalkiran.cdcdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class CdcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdcDemoApplication.class, args);
	}

}
