package com.hespinoza.designpatterns.strategy.example02.strategy.impl;

import com.hespinoza.designpatterns.strategy.example02.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public String pay(double amount) {
        return "Paid " + amount + " using Credit Card.";
    }
}
