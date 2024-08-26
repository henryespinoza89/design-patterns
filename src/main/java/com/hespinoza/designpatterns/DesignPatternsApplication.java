package com.hespinoza.designpatterns;

import com.hespinoza.designpatterns.fintech.domain.impl.SingletonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesignPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignPatternsApplication.class, args);

		SingletonConfig config = SingletonConfig.getInstance(); // Get the single SingletonConfig instance
		config.setAppName("My Spring Boot Application");
		config.setMaxConnections(200);
	}
}
