package jp.co.systena.tigerscave.rpgapplication.model;

public abstract class BaseJob {
  public static final String WARRIOR = "戦士";
  public static final String WITCH = "魔法使い";
  public static final String MARTIAL_ARTIST = "武闘家";

  protected String name;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract String fight();
}
