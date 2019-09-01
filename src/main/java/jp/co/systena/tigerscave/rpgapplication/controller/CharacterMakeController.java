package jp.co.systena.tigerscave.rpgapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpgapplication.model.CharacterMakeForm;

@Controller
public class CharacterMakeController {

  @RequestMapping(value = "/charactermake", method = RequestMethod.GET)
  public ModelAndView show(ModelAndView mav) {
    CharacterMakeForm characterMakeForm = new CharacterMakeForm();
    mav.addObject("character", characterMakeForm);


    mav.setViewName("CharacterMake");

    return mav;
  }

}
