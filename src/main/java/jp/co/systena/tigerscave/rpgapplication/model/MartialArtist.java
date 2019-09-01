package jp.co.systena.tigerscave.rpgapplication.model;

public class MartialArtist extends BaseJob {

  public MartialArtist() {

  }

  public MartialArtist(String name) {
    this.name = name;
  }

  public String fight() {
    StringBuilder builder = new StringBuilder();
    builder.append(name);
    builder.append("は拳で攻撃した！");

    return builder.toString();
  }
}
