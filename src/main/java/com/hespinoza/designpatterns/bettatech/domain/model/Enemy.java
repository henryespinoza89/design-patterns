package com.hespinoza.designpatterns.bettatech.domain.model;

public abstract class Enemy {
  private String enemyId;
  private String name;
  private String power;
  private String weaknesses;
  private String strengths;
  public String getEnemyId() { return enemyId; }
  public void setEnemyId(String enemyId) { this.enemyId = enemyId; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getPower() { return power; }
  public void setPower(String power) { this.power = power; }
  public String getWeaknesses() { return weaknesses; }
  public void setWeaknesses(String weaknesses) { this.weaknesses = weaknesses; }
  public String getStrengths() { return strengths; }
  public void setStrengths(String strengths) { this.strengths = strengths; }
  public abstract String power();
  public abstract String weaknesses();
  public abstract double strengths();
}
