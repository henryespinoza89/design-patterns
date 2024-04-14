package com.hespinoza.designpatterns.fintech.factory;

import com.hespinoza.designpatterns.fintech.application.impl.CheckingAccountServiceImpl;
import com.hespinoza.designpatterns.fintech.domain.model.Account;

public class CheckingAccountFactory implements AccountFactory {
  @Override
  public Account createAccount() {
    return new CheckingAccountServiceImpl();
  }
}
