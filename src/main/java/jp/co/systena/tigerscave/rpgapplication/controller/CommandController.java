package jp.co.systena.tigerscave.rpgapplication.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpgapplication.model.BaseJob;
import jp.co.systena.tigerscave.rpgapplication.model.CharacterMakeForm;
import jp.co.systena.tigerscave.rpgapplication.model.MartialArtist;
import jp.co.systena.tigerscave.rpgapplication.model.Warrior;
import jp.co.systena.tigerscave.rpgapplication.model.Witch;

@Controller
public class CommandController {

  @Autowired
  HttpSession session;

  @RequestMapping(value = "/command", method = RequestMethod.GET)
  public ModelAndView show(ModelAndView mav) {

    CharacterMakeForm characterDispData = new CharacterMakeForm();

    //戦士表示
    Warrior warrior = (Warrior) session.getAttribute(BaseJob.WARRIOR);
    if (warrior != null) {
      characterDispData.setName(warrior.getName());
      characterDispData.setJob(BaseJob.WARRIOR);
    }

    //魔法使い表示
    Witch witch = (Witch) session.getAttribute(BaseJob.WITCH);
    if (witch != null) {
      characterDispData.setName(witch.getName());
      characterDispData.setJob(BaseJob.WITCH);
    }

    //武闘家表示
    MartialArtist martialArtist = (MartialArtist) session.getAttribute(BaseJob.MARTIAL_ARTIST);
    if (martialArtist != null) {
      characterDispData.setName(martialArtist.getName());
      characterDispData.setJob(BaseJob.MARTIAL_ARTIST);
    }


    mav.addObject("character", characterDispData);

    mav.setViewName("Command");

    return mav;
  }

  @RequestMapping(value = "/command", method = RequestMethod.POST)
  public ModelAndView makedCharacter(ModelAndView mav, CharacterMakeForm characterInput) {
    String name = characterInput.getName();
    String job = characterInput.getJob();

    // 職業に応じてキャラクターをセッションに保存
    switch (job) {
      case BaseJob.WARRIOR:
        Warrior warrior = new Warrior(name);
        session.setAttribute(BaseJob.WARRIOR, warrior);
        session.removeAttribute(BaseJob.WITCH);
        session.removeAttribute(BaseJob.MARTIAL_ARTIST);
        break;
      case BaseJob.WITCH:
        Witch witch = new Witch(name);
        session.setAttribute(BaseJob.WITCH, witch);
        session.removeAttribute(BaseJob.WARRIOR);
        session.removeAttribute(BaseJob.MARTIAL_ARTIST);
        break;
      case BaseJob.MARTIAL_ARTIST:
        MartialArtist martialArtist = new MartialArtist(name);
        session.setAttribute(BaseJob.MARTIAL_ARTIST, martialArtist);
        session.removeAttribute(BaseJob.WARRIOR);
        session.removeAttribute(BaseJob.WITCH);
        break;
    }

    return new ModelAndView("redirect:/command"); // リダイレクト
  }

}
