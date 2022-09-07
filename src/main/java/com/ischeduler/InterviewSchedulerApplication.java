package com.ischeduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.ischeduler.model")
@EnableJpaRepositories("com.ischeduler.repository")
@SpringBootApplication(scanBasePackages = 
	{ "com.ischeduler.api, com.ischeduler.service, com.ischeduler.validation" })
public class InterviewSchedulerApplication {
	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/scheduler");
		SpringApplication.run(InterviewSchedulerApplication.class, args);
	}
}
