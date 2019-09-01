package jp.co.systena.tigerscave.rpgapplication.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpgapplication.model.BaseJob;
import jp.co.systena.tigerscave.rpgapplication.model.ResultForm;
import jp.co.systena.tigerscave.rpgapplication.model.Warrior;
import jp.co.systena.tigerscave.rpgapplication.model.Witch;

@Controller
public class ResultController {

  @Autowired
  HttpSession session;

  @RequestMapping(value = "/result", method = RequestMethod.GET)
  public ModelAndView show(ModelAndView mav) {

    // 戦士取得
    BaseJob baseJob = (Warrior) session.getAttribute(BaseJob.Warrior);
    if (baseJob == null) {
      // 魔法使い取得
      baseJob = (Witch) session.getAttribute(BaseJob.Witch);
    }

    ResultForm resultForm = new ResultForm(baseJob.fight());
    mav.addObject("result", resultForm);

    mav.setViewName("Result");

    return mav;
  }

  @RequestMapping(value = "/result", method = RequestMethod.POST)
  public ModelAndView makedCharacter(ModelAndView mav) {

    return new ModelAndView("redirect:/result"); // リダイレクト
  }

}
