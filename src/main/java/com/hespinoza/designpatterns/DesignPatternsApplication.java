package com.hespinoza.designpatterns;

import com.hespinoza.designpatterns.strategy.example01.service.PaymentService;
import com.hespinoza.designpatterns.strategy.example01.strategy.CreditCardStrategy;
import com.hespinoza.designpatterns.strategy.example01.strategy.PayPalStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesignPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignPatternsApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext ctx) {
		return args -> {
			PaymentService paymentService = ctx.getBean(PaymentService.class);

			paymentService.setPaymentStrategy(new CreditCardStrategy("1234-5678-9876-5432", "John Doe"));
			paymentService.processPayment(150.0);

			paymentService.setPaymentStrategy(new PayPalStrategy("john.doe@example.com"));
			paymentService.processPayment(85.5);
		};
	}
}
