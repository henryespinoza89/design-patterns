package com.hespinoza.designpatterns.fintech.domain.model;

import java.math.BigDecimal;
public abstract class Account {
  // Clases abstractas: Son clases que no se pueden instanciar por sí mismas porque están diseñadas para ser clases
  // base de otras clases. Una clase abstracta puede contener métodos abstractos (que no tienen implementación y deben
  // ser implementados por las clases derivadas) así como métodos con implementación completa (métodos concretos). El
  // propósito de una clase abstracta es proporcionar una plantilla o un esqueleto para clases derivadas que amplíen
  // y concreten su funcionalidad.
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
