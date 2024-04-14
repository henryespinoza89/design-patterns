package com.hespinoza.designpatterns.fintech.factory;

import com.hespinoza.designpatterns.fintech.application.impl.SavingsAccountServiceImpl;
import com.hespinoza.designpatterns.fintech.domain.model.Account;

public class SavingsAccountFactory implements AccountFactory {
  // En el ejemplo que proporcioné, aunque el cliente elige un ConcreteCreator específico (como SavingsAccountFactory o
  // CheckingAccountFactory), lo importante es que el tipo de producto que recibe de estos creadores se maneja a través
  // de la interfaz Account. De este modo, el cliente no necesita saber si está trabajando con un SavingsAccountFactory
  // o un InvestmentAccountFactory; solo sabe que está manejando objetos que implementan la interfaz Account.
  // Esto le permite operar de manera genérica sobre los productos, reduciendo el acoplamiento entre el cliente y
  // las clases concretas de los productos.
  @Override
  public Account createAccount() {
    return new SavingsAccountServiceImpl();
  }
}
