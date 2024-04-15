package com.hespinoza.designpatterns.bettatech.factory;

import com.hespinoza.designpatterns.bettatech.application.impl.Goomba;
import com.hespinoza.designpatterns.bettatech.domain.model.Enemy;

public class GoombaFactory implements EnemyFactory {
  @Override
  public Enemy createEnemy() {
    return new Goomba();
  }
}
