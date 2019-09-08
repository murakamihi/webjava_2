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
public class CharacterMakeController {

  @Autowired
  HttpSession session;

  @RequestMapping(value = "/charactermake", method = RequestMethod.GET)
  public ModelAndView show(ModelAndView mav) {
    CharacterMakeForm characterMakeForm = new CharacterMakeForm();
    Party party = (Party) session.getAttribute(Party.PARTY_SESSION_KEY);
    if (party == null) {
      party = new Party();
    }

    party.checkDisplayContinued(characterMakeForm);

    mav.addObject("character", characterMakeForm);


    mav.setViewName("CharacterMake");

    return mav;
  }

}
