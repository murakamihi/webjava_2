package jp.co.systena.tigerscave.rpgapplication.model;

public class Warrior extends BaseJob {

  public Warrior() {

  }

  public Warrior(String name) {
    this.name = name;
  }

  public Warrior(String name, String action) {
    this.name = name;
    this.action = action;
  }

  public String fight() {
    StringBuilder builder = new StringBuilder();
    builder.append(name);
    builder.append("は剣で攻撃した！　HP：-10");

    return builder.toString();
  }

  public String strongFight() {
    StringBuilder builder = new StringBuilder();
    builder.append(name);
    builder.append("は剣で強攻撃した！　HP：-20");

    return builder.toString();
  }

  public String recovery() {
    StringBuilder builder = new StringBuilder();
    builder.append(name);
    builder.append("はやくそうで回復した！");

    return builder.toString();
  }
}
