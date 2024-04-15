package com.hespinoza.designpatterns.bettatech.application.impl;

import com.hespinoza.designpatterns.bettatech.domain.model.Enemy;

public final class Goomba extends Enemy {
  public Goomba() {
    setName("Goomba");
  }
  @Override
  public String power() {
    setPower("Tengo el poder para alcanzar objetos que se encuentren a gran altura.");
    return getPower();
  }

  @Override
  public String weaknesses() {
    setWeaknesses("Somos pequeños y nos puedes aplastar.");
    return getWeaknesses();
  }

  @Override
  public double strengths() {
    return 100;
  }
}
