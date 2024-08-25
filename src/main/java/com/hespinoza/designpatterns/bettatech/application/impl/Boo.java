package com.hespinoza.designpatterns.bettatech.application.impl;

import com.hespinoza.designpatterns.bettatech.domain.model.Enemy;

public final class Boo extends Enemy {
  public Boo() {
    setName("boo");
  }
  @Override
  public String power() {
    setPower("I have the power to break through walls and weaken my enemy.");
    return getPower();
  }

  @Override
  public String weaknesses() {
    setWeaknesses("I have short, clumsy hands and no fingers.");
    return getWeaknesses();
  }

  @Override
  public double strengths() {
    return 50;
  }
}
