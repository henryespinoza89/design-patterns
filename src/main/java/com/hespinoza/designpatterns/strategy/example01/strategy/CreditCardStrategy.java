package com.hespinoza.designpatterns.strategy.example01.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCardStrategy implements PaymentStrategy {

    private static final Logger log = LoggerFactory.getLogger(CreditCardStrategy.class);

    private final String cardNumber;
    private final String cardHolderName;

    public CreditCardStrategy(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(double amount) {
        log.info("Paid {} using Credit Card (Card Number: {})", amount, cardNumber);
    }
}
