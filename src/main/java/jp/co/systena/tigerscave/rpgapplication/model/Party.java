package jp.co.systena.tigerscave.rpgapplication.model;

import java.util.ArrayList;
import java.util.List;

public class Party {
  public static final String PARTY_SESSION_KEY = "PARTY_SESSION_KEY";
  public static final int MAX_MEMBER = 2;

  List<Object> partyMember;
  int numberOfPeople;
  int dispNumber;

  public Party() {
    partyMember = new ArrayList<Object>();
  }

  public int getNumberOfPeople() {
    return numberOfPeople;
  }

  public void setNumberOfPeople(int numberOfPeople) {
    this.numberOfPeople = numberOfPeople;
  }

  public int getDispNumber() {
    return dispNumber;
  }

  public void setDispNumber(int dispNumber) {
    this.dispNumber = dispNumber;
  }

  public void addDispNumber() {
    dispNumber++;
  }

  public List<Object> getPartyMember() {
    return partyMember;
  }

  public void setPartyMember(List<Object> partyMember) {
    this.partyMember = partyMember;
  }

  public CharacterMakeForm setDispData(CharacterMakeForm dispData) {
    Object member = partyMember.get(dispNumber);

    if (member instanceof Warrior) {
      dispData.setName(((Warrior) member).getName());
      dispData.setJob(BaseJob.WARRIOR);
    } else if (member instanceof Witch) {
      dispData.setName(((Witch) member).getName());
      dispData.setJob(BaseJob.WITCH);
    } else if (member instanceof MartialArtist) {
      dispData.setName(((MartialArtist) member).getName());
      dispData.setJob(BaseJob.MARTIAL_ARTIST);
    }

    return dispData;
  }

  public void addMember(String name, String job) {
    numberOfPeople++;
    // 職業に応じてキャラクターを保存
    switch (job) {
      case BaseJob.WARRIOR:
        Warrior warrior = new Warrior(name);
        partyMember.add(warrior);
        break;
      case BaseJob.WITCH:
        Witch witch = new Witch(name);
        partyMember.add(witch);
        break;
      case BaseJob.MARTIAL_ARTIST:
        MartialArtist martialArtist = new MartialArtist(name);
        partyMember.add(martialArtist);
        break;
    }
  }

  public void checkDisplayContinued(CharacterMakeForm dispData) {
    if (numberOfPeople + 1 >= MAX_MEMBER) {
      dispData.isDisplayContinued = false;
    }
  }
}
