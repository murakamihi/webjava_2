package jp.co.systena.tigerscave.rpgapplication.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpgapplication.model.CharacterMakeForm;
import jp.co.systena.tigerscave.rpgapplication.model.Party;

@Controller
public class CommandController {

  @Autowired
  HttpSession session;

  @RequestMapping(value = "/command", method = RequestMethod.GET)
  public ModelAndView show(ModelAndView mav) {

    CharacterMakeForm characterDispData = new CharacterMakeForm();
    Party party = (Party) session.getAttribute(Party.PARTY_SESSION_KEY);

    characterDispData = party.setDispData(characterDispData);

    mav.addObject("character", characterDispData);

    mav.setViewName("Command");

    return mav;
  }

  @RequestMapping(value = "/command", params = "next", method = RequestMethod.POST)
  public ModelAndView makedCharacter(ModelAndView mav, CharacterMakeForm characterInput) {
    // パーティーに追加＋セッション保存
    addMemberAndSaveSession(characterInput.getName(), characterInput.getJob());

    return new ModelAndView("redirect:/command"); // リダイレクト
  }

  @RequestMapping(value = "/command", params = "repeat", method = RequestMethod.POST)
  public ModelAndView reMakedCharacter(ModelAndView mav, CharacterMakeForm characterInput) {
    // パーティーに追加＋セッション保存
    addMemberAndSaveSession(characterInput.getName(), characterInput.getJob());

    return new ModelAndView("redirect:/charactermake"); // リダイレクト
  }

  private void addMemberAndSaveSession(String name, String job) {
    Party party = (Party) session.getAttribute(Party.PARTY_SESSION_KEY);
    if (party == null) {
      party = new Party();
    }
    // パーティーに追加
    party.addMember(name, job);

    session.setAttribute(Party.PARTY_SESSION_KEY, party);
  }
}
