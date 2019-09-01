package jp.co.systena.tigerscave.rpgapplication.model;

public abstract class BaseJob {
  public static final String Warrior = "戦士";
  public static final String Witch = "魔法使い";

  protected String name;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract String fight();
}
