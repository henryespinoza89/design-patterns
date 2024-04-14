package com.hespinoza.designpatterns.fintech.factory;

import com.hespinoza.designpatterns.fintech.application.impl.InvestmentAccountServiceImpl;
import com.hespinoza.designpatterns.fintech.domain.model.Account;

public class InvestmentAccountFactory implements AccountFactory {
  @Override
  public Account createAccount() {
    return new InvestmentAccountServiceImpl();
  }
}
