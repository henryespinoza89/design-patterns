package com.hespinoza.designpatterns.bettatech.application.impl;

import com.hespinoza.designpatterns.bettatech.domain.model.Enemy;

public final class Koopa extends Enemy {
  public Koopa() {
    setName("Koopa");
  }
  @Override
  public String power() {
    setPower("Tengo el poder para escupir bolas de fuego o lanzallamas.");
    return getPower();
  }

  @Override
  public String weaknesses() {
    setWeaknesses("Nos puedes aplastar en 3 intentos.");
    return getWeaknesses();
  }

  @Override
  public double strengths() {
    return 150;
  }
}
