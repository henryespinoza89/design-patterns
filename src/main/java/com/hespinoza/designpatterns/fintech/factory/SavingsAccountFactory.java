package com.hespinoza.designpatterns.fintech.factory;

import com.hespinoza.designpatterns.fintech.application.impl.SavingsAccountServiceImpl;
import com.hespinoza.designpatterns.fintech.domain.model.Account;

public class SavingsAccountFactory implements AccountFactory {
  @Override
  public Account createAccount() {
    return new SavingsAccountServiceImpl();
  }
}
