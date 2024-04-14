package com.hespinoza.designpatterns.fintech.factory;

import com.hespinoza.designpatterns.fintech.domain.model.Account;

public interface AccountFactory {
  // Interfaces: Las interfaces son una forma de especificar que una clase debe implementar ciertos métodos, pero
  // como las interfaces no pueden tener ninguna implementación concreta de métodos (hasta antes de Java 8, después
  // se pueden tener métodos default y estáticos con implementaciones), tampoco se pueden instanciar. Las interfaces
  // definen un contrato que las clases deben seguir, sin proporcionar ninguna funcionalidad concreta.
  Account createAccount();
}
