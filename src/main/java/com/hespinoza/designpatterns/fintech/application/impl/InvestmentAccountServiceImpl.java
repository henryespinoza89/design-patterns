package com.hespinoza.designpatterns.fintech.application.impl;

import com.hespinoza.designpatterns.fintech.domain.model.Account;

import java.math.BigDecimal;

public class InvestmentAccountServiceImpl extends Account {
  private final BigDecimal balance;
  private final BigDecimal interestRate;
  public InvestmentAccountServiceImpl() {
    balance = BigDecimal.ZERO; // Balance inicial cero
    interestRate = new BigDecimal("0.05"); // Interes rate at 5%
  }
  @Override
  public void deposit(BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) > 0) {
      setBalance(balance.add(amount));
    } else {
      throw new IllegalArgumentException("The amount to be deposited must be positive.");
    }
  }
  @Override
  public void withdraw(BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) > 0 &&  getBalance().compareTo(amount) >= 0) {
      setBalance(getBalance().subtract(amount));
    } else {
      throw new IllegalArgumentException("Insufficient funds or incorrect amount.");
    }
  }

  @Override
  public double calculateInterest() {
    return getBalance().doubleValue() * interestRate.doubleValue();
  }
}
