package com.hespinoza.designpatterns.strategy.example01.service;

import com.hespinoza.designpatterns.strategy.example01.strategy.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set.");
        }
        paymentStrategy.pay(amount);
    }
}
