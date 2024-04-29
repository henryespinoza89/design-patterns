package com.hespinoza.designpatterns;

import com.hespinoza.designpatterns.fintech.domain.impl.SingletonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesignPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignPatternsApplication.class, args);

		// Obtener la instancia única de SingletonConfig
		SingletonConfig config = SingletonConfig.getInstance();
		// Configurar algunos valores
		config.setAppName("Mi Aplicación Spring Boot");
		config.setMaxConnections(200);
	}

}
