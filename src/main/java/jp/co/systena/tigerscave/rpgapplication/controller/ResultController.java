package jp.co.systena.tigerscave.rpgapplication.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpgapplication.model.BaseJob;
import jp.co.systena.tigerscave.rpgapplication.model.MartialArtist;
import jp.co.systena.tigerscave.rpgapplication.model.ResultForm;
import jp.co.systena.tigerscave.rpgapplication.model.Warrior;
import jp.co.systena.tigerscave.rpgapplication.model.Witch;

@Controller
public class ResultController {

  @Autowired
  HttpSession session;

  @RequestMapping(value = "/result", method = RequestMethod.GET)
  public ModelAndView show(ModelAndView mav) {

    BaseJob baseJob = getJob();

    ResultForm resultForm = new ResultForm();
    if (baseJob.getAction().equals(BaseJob.FIGHT)) {
      // たかかう選択
      resultForm.setResultString(baseJob.fight());
    } else {
      // かいふく選択
      resultForm.setResultString(baseJob.recovery());
    }
    mav.addObject("result", resultForm);

    mav.setViewName("Result");

    return mav;
  }

  @RequestMapping(value = "/result", params = "fight", method = RequestMethod.POST)
  public ModelAndView commandFight(ModelAndView mav) {

    BaseJob baseJob = getJob();
    baseJob.setAction(BaseJob.FIGHT);
    if (baseJob instanceof Warrior) {
      session.setAttribute(BaseJob.WARRIOR, baseJob);
    } else if (baseJob instanceof Witch) {
      session.setAttribute(BaseJob.WITCH, baseJob);
    } else {
      session.setAttribute(BaseJob.MARTIAL_ARTIST, baseJob);
    }

    return new ModelAndView("redirect:/result"); // リダイレクト
  }

  @RequestMapping(value = "/result", params = "recovery", method = RequestMethod.POST)
  public ModelAndView commandRecovery(ModelAndView mav) {

    BaseJob baseJob = getJob();
    baseJob.setAction(BaseJob.RECOVERY);
    if (baseJob instanceof Warrior) {
      session.setAttribute(BaseJob.WARRIOR, baseJob);
    } else if (baseJob instanceof Witch) {
      session.setAttribute(BaseJob.WITCH, baseJob);
    } else {
      session.setAttribute(BaseJob.MARTIAL_ARTIST, baseJob);
    }

    return new ModelAndView("redirect:/result"); // リダイレクト
  }

  private BaseJob getJob() {
    // 戦士取得
    BaseJob baseJob = (Warrior) session.getAttribute(BaseJob.WARRIOR);
    if (baseJob == null) {
      // 魔法使い取得
      baseJob = (Witch) session.getAttribute(BaseJob.WITCH);
    }
    if (baseJob == null) {
      // 武闘家取得
      baseJob = (MartialArtist) session.getAttribute(BaseJob.MARTIAL_ARTIST);
    }

    return baseJob;
  }

}
