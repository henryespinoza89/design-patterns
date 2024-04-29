package com.hespinoza.designpatterns.fintech.domain.impl;

import org.springframework.stereotype.Component;

@Component
public class SingletonCounter {
  // OJO: para que Spring administre SingletonCounter como un Singleton
  // simplemente agregamos la anotación @Component a la clase.

  // Esta es una clase que representa un contador y asegura que solo haya una
  // instancia de ese contador en toda la aplicación.
  // Se define una variable estática instance que almacenará la única instancia
  // de la clase.
  private static SingletonCounter instance;
  private int count;

  // Constructor privado para evitar instanciación externa
  private SingletonCounter() {
    count = 0;
  }

  // Método estático para obtener la única instancia
  public static SingletonCounter getInstance() {
    if (instance == null) {
      instance = new SingletonCounter();
    }
    return instance;
  }

  // Métodos para operar el contador
  public void increment() {
    count++;
  }
  public void decrement() {
    count--;
  }
  public int getCount() {
    return count;
  }
  // Otros casos de uso: configuración de la aplicación, mantener una conexión
  // de base de datos, etc.
}
