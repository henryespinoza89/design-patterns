package com.hespinoza.designpatterns.fintech.domain.impl;

import org.springframework.stereotype.Component;

@Component
public class SingletonCounter {
  private static SingletonCounter instance;
  private int count;

  private SingletonCounter() {
    count = 0;
  }

  public static SingletonCounter getInstance() {
    if (instance == null) {
      instance = new SingletonCounter();
    }
    return instance;
  }

  public void increment() {
    count++;
  }
  public void decrement() {
    count--;
  }
  public int getCount() {
    return count;
  }
}
