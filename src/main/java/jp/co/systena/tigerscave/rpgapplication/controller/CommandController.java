package jp.co.systena.tigerscave.rpgapplication.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpgapplication.model.BaseJob;
import jp.co.systena.tigerscave.rpgapplication.model.CharacterMakeForm;
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
    Warrior warrior = (Warrior) session.getAttribute(BaseJob.Warrior);
    if (warrior != null) {
      characterDispData.setName(warrior.getName());
      characterDispData.setJob(BaseJob.Warrior);
    }

    //魔法使い表示
    Witch witch = (Witch) session.getAttribute(BaseJob.Witch);
    if (witch != null) {
      characterDispData.setName(witch.getName());
      characterDispData.setJob(BaseJob.Witch);
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
      case BaseJob.Warrior:
        Warrior warrior = new Warrior(name);
        session.setAttribute(BaseJob.Warrior, warrior);
        session.removeAttribute(BaseJob.Witch);
        break;
      case BaseJob.Witch:
        Witch witch = new Witch(name);
        session.setAttribute(BaseJob.Witch, witch);
        session.removeAttribute(BaseJob.Warrior);
        break;
    }

    return new ModelAndView("redirect:/command"); // リダイレクト
  }

}
