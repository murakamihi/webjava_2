package jp.co.systena.tigerscave.rpgapplication.model;

public abstract class BaseJob {
  public static final String WARRIOR = "戦士";
  public static final String WITCH = "魔法使い";
  public static final String MARTIAL_ARTIST = "武闘家";

  public static final String FIGHT = "たたかう";
  public static final String RECOVERY = "かいふく";

  protected String name;
  protected String action;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getAction() {
    return action;
  }

  public abstract String fight();
  public abstract String strongFight();
  public abstract String recovery();
}
