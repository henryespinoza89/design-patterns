package com.hespinoza.designpatterns.strategy.example01.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayPalStrategy implements PaymentStrategy {

    private static final Logger log = LoggerFactory.getLogger(PayPalStrategy.class);

    private final String email;

    public PayPalStrategy(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        log.info("Paid {} using PayPal (Email: {})", amount, email);
    }
}
