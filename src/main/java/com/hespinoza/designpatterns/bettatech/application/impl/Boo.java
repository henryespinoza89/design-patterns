package com.hespinoza.designpatterns.bettatech.application.impl;

import com.hespinoza.designpatterns.bettatech.domain.model.Enemy;

public final class Boo extends Enemy {
  public Boo() {
    setName("boo");
  }
  @Override
  public String power() {
    setPower("Tengo el poder para atravesar las paredes y debilidar a mi enemigo.");
    return getPower();
  }

  @Override
  public String weaknesses() {
    setWeaknesses("Tengo manos cortas y torpes y no tengo dedos.");
    return getWeaknesses();
  }

  @Override
  public double strengths() {
    return 50;
  }
}
