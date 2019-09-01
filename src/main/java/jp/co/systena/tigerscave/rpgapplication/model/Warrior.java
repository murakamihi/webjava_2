package jp.co.systena.tigerscave.rpgapplication.model;

public class Warrior extends BaseJob {

  public Warrior() {

  }

  public Warrior(String name) {
    this.name = name;
  }

  public String fight() {
    StringBuilder builder = new StringBuilder();
    builder.append(name);
    builder.append("は剣で攻撃した！");

    return builder.toString();
  }
}
