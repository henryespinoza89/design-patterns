package com.hespinoza.designpatterns.bettatech.factory;

import com.hespinoza.designpatterns.bettatech.application.impl.Boo;
import com.hespinoza.designpatterns.bettatech.application.impl.Goomba;
import com.hespinoza.designpatterns.bettatech.application.impl.Koopa;
import com.hespinoza.designpatterns.bettatech.domain.model.Enemy;

public class RandomFactory implements EnemyRandomFactory {
  @Override
  public Enemy createEnemyRamdonFactory() {
    Enemy enemy;
    Double randomNum = Math.random();
    if (randomNum > 0.00 && randomNum < 0.30) {
      enemy = new Koopa();
    } else if (randomNum >= 0.30 && randomNum < 0.80) {
      enemy = new Goomba();
    } else {
      enemy = new Boo();
    }
    return enemy;
  }
}
