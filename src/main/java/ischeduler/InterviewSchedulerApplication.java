package ischeduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("ischeduler.model")
@EnableJpaRepositories("ischeduler.repository")
@SpringBootApplication(scanBasePackages = 
	{ "ischeduler.api, ischeduler.service, ischeduler.validation, ischeduler.repository" })
public class InterviewSchedulerApplication {
	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/scheduler");
		SpringApplication.run(InterviewSchedulerApplication.class, args);
	}
}
