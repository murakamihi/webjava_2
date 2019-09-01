package jp.co.systena.tigerscave.rpgapplication.model;

public class CharacterMakeForm {
  String job;
  String name;

  public CharacterMakeForm() {
    // 初期値は戦士とする
    job = BaseJob.WARRIOR;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public String getJob() {
    return job;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
