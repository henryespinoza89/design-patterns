package com.hespinoza.designpatterns.fintech.domain.model;

import java.math.BigDecimal;
public abstract class Account {
  private String accountId;
  private BigDecimal balance;
  public String getAccountId() { return accountId; }
  public void setAccountId(String accountId) { this.accountId = accountId; }
  public BigDecimal getBalance() { return balance; }
  public void setBalance(BigDecimal balance) { this.balance = balance; }
  public abstract void deposit(BigDecimal amount);
  public abstract void withdraw(BigDecimal amount);
  public abstract double calculateInterest();
}
