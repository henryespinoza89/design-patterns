package com.hespinoza.designpatterns.strategy.example02.controller;

import com.hespinoza.designpatterns.strategy.example02.service.PaymentServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentServices paymentServices;

    public PaymentController(PaymentServices paymentServices) {
        this.paymentServices = paymentServices;
    }

    @GetMapping("/pay")
    public String pay(@RequestParam String paymentType, @RequestParam double amount) {
        return paymentServices.processPayment(paymentType, amount);
    }
}
