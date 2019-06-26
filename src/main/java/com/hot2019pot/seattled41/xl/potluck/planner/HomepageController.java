package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomepageController {

    @Autowired
    PotluckUserRepository potLuckUserRepository;

    @GetMapping("/")
  public String getRoot(Principal p, Model m ){
        if(p!=null){
            if(potLuckUserRepository.findByUsername(p.getName()).createPotlucks.size()>0){
                m.addAttribute("mypotlucks",potLuckUserRepository.findByUsername(p.getName()).createPotlucks);
            }

            if(potLuckUserRepository.findByUsername(p.getName()).attendingPotlucks.size()>0){
                m.addAttribute("attendingpotlucks",potLuckUserRepository.findByUsername(p.getName()).attendingPotlucks);
            }
            m.addAttribute("principal", p);
            m.addAttribute("user",potLuckUserRepository.findByUsername(p.getName()));
        }
      return "homepage";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
