package ischeduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = 
	{ "ischeduler.api, ischeduler.service, ischeduler.validation" })
public class InterviewSchedulerApplication {
	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/scheduler");
		SpringApplication.run(InterviewSchedulerApplication.class, args);
	}
}
