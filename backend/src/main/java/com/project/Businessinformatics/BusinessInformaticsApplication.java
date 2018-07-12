package com.project.Businessinformatics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BusinessInformaticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessInformaticsApplication.class, args);
	}
}
