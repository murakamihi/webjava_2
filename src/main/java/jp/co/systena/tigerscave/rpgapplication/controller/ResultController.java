package jp.co.systena.tigerscave.rpgapplication.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpgapplication.model.BaseJob;
import jp.co.systena.tigerscave.rpgapplication.model.Enemy;
import jp.co.systena.tigerscave.rpgapplication.model.Party;
import jp.co.systena.tigerscave.rpgapplication.model.ResultForm;

@Controller
public class ResultController {

  @Autowired
  HttpSession session;

  @RequestMapping(value = "/result", method = RequestMethod.GET)
  public ModelAndView show(ModelAndView mav) {
    Party party = (Party) session.getAttribute(Party.PARTY_SESSION_KEY);
    List<Object> partyMember = party.getPartyMember();
    ResultForm resultForm = new ResultForm();
    Enemy enemy = new Enemy();
    for (Object member : partyMember) {
      BaseJob baseJob = (BaseJob) member;
      if (baseJob.getAction().equals(BaseJob.FIGHT)) {
        // たかかう選択
        resultForm.addResult(baseJob.fight());
        enemy.damage();
      } else {
        // かいふく選択
        resultForm.addResult(baseJob.recovery());
      }
    }
    mav.addObject("result", resultForm);
    mav.addObject("enemy", enemy);

    mav.setViewName("Result");

    session.removeAttribute(Party.PARTY_SESSION_KEY);

    return mav;
  }

  @RequestMapping(value = "/result", params = "fight", method = RequestMethod.POST)
  public ModelAndView commandFight(ModelAndView mav) {
    Party party = (Party) session.getAttribute(Party.PARTY_SESSION_KEY);

    List<Object> partyMember = party.getPartyMember();
    int dispNumber = party.getDispNumber();
    BaseJob baseJob = (BaseJob) partyMember.get(dispNumber);
    baseJob.setAction(BaseJob.FIGHT);
    partyMember.set(dispNumber, baseJob);

    session.setAttribute(Party.PARTY_SESSION_KEY, party);

    // パーティー人数分表示されたら結果画面へ遷移
    if (party.getNumberOfPeople() > dispNumber + 1) {
      party.addDispNumber();
      return new ModelAndView("redirect:/command");
    }
    return new ModelAndView("redirect:/result"); // リダイレクト
  }

  @RequestMapping(value = "/result", params = "recovery", method = RequestMethod.POST)
  public ModelAndView commandRecovery(ModelAndView mav) {
    Party party = (Party) session.getAttribute(Party.PARTY_SESSION_KEY);

    List<Object> partyMember = party.getPartyMember();
    int dispNumber = party.getDispNumber();
    BaseJob baseJob = (BaseJob) partyMember.get(dispNumber);
    baseJob.setAction(BaseJob.RECOVERY);
    partyMember.set(dispNumber, baseJob);

    session.setAttribute(Party.PARTY_SESSION_KEY, party);

    // パーティー人数分表示されたら結果画面へ遷移
    if (party.getNumberOfPeople() > dispNumber + 1) {
      party.addDispNumber();
      return new ModelAndView("redirect:/command");
    }
    return new ModelAndView("redirect:/result"); // リダイレクト
  }
}
