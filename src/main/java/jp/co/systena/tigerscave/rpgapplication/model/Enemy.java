package jp.co.systena.tigerscave.rpgapplication.model;

public class Enemy {
  public static final String ENEMY_KEY = "ENEMY_KEY";
  int hp = 100;

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public void damage() {
    hp = hp - 10;
  }
}
