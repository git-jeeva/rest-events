package com.jeeva.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestAuditApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestAuditApplication.class, args);
	}
}
