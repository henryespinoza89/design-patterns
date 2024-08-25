package com.hespinoza.designpatterns.bettatech.application.impl;

import com.hespinoza.designpatterns.bettatech.domain.model.Enemy;

public final class Goomba extends Enemy {
  public Goomba() {
    setName("Goomba");
  }
  @Override
  public String power() {
    setPower("I have the power to reach objects that are at great heights.");
    return getPower();
  }

  @Override
  public String weaknesses() {
    setWeaknesses("We are small and you can crush us.");
    return getWeaknesses();
  }

  @Override
  public double strengths() {
    return 100;
  }
}
