package com.hespinoza.designpatterns.strategy.example02.service;

import com.hespinoza.designpatterns.strategy.example02.strategy.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PaymentServices {

    private final List<PaymentStrategy> paymentStrategies;

    public String processPayment(String paymentType, double amount) {
        for (PaymentStrategy strategy : paymentStrategies) {
            if (strategy.getClass().getSimpleName().toLowerCase(Locale.ROOT)
                    .contains(paymentType.toLowerCase(Locale.ROOT))) {
                return strategy.pay(amount);
            }
        }
        throw new IllegalArgumentException("No valid payment strategy found for: " + paymentType);
    }
}
