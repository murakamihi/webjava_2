package jp.co.systena.tigerscave.rpgapplication.model;

public class Enemy {
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
