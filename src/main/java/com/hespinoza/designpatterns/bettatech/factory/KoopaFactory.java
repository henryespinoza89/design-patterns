package com.hespinoza.designpatterns.bettatech.factory;

import com.hespinoza.designpatterns.bettatech.application.impl.Koopa;
import com.hespinoza.designpatterns.bettatech.domain.model.Enemy;

public class KoopaFactory implements EnemyFactory {
  @Override
  public Enemy createEnemy() {
    return new Koopa();
  }
}
