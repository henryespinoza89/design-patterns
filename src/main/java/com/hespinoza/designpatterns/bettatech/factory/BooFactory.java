package com.hespinoza.designpatterns.bettatech.factory;

import com.hespinoza.designpatterns.bettatech.application.impl.Boo;
import com.hespinoza.designpatterns.bettatech.domain.model.Enemy;

public class BooFactory implements EnemyFactory {
  @Override
  public Enemy createEnemy() {
    return new Boo();
  }
}
