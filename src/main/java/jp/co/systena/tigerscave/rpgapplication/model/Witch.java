package jp.co.systena.tigerscave.rpgapplication.model;

public class Witch extends BaseJob {

  public Witch() {

  }

  public Witch(String name) {
    this.name = name;
  }

  public String fight() {
    StringBuilder builder = new StringBuilder();
    builder.append(name);
    builder.append("はまほうで攻撃した！");

    return builder.toString();
  }
}
