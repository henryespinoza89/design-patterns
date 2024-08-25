package com.hespinoza.designpatterns.bettatech.application.impl;

import com.hespinoza.designpatterns.bettatech.domain.model.Enemy;

public final class Koopa extends Enemy {
  public Koopa() {
    setName("Koopa");
  }
  @Override
  public String power() {
    setPower("I have the power to spit fireballs or flamethrowers.");
    return getPower();
  }

  @Override
  public String weaknesses() {
    setWeaknesses("You can crush us in 3 tries.");
    return getWeaknesses();
  }

  @Override
  public double strengths() {
    return 150;
  }
}
