package com.hespinoza.designpatterns.fintech.factory;

import com.hespinoza.designpatterns.fintech.domain.model.Account;

public interface AccountFactory {
  Account createAccount();
}
