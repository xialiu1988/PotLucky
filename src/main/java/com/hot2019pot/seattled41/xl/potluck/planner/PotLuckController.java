package com.hot2019pot.seattled41.xl.potluck.planner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PotLuckController {
    @Autowired
    PotLuckRepository potLuckRepository;
    @Autowired
    PotLuckUserRepository potLuckUserRepository;

    @GetMapping("/Potlucks/{id}")
    public String getDetails(@PathVariable Long id, Principal P, Model model){
        PotLuck thePotluck=potLuckRepository.findById(id).get();
        model.addAttribute("newPotluck",thePotluck);
        return "potLuckDetail";
    }




@GetMapping("/search")
    public String getOnepotluck(String code,Principal p,Model m){

        PotLuck pp = potLuckRepository.findByCode(code);


        if(pp.creator!=potLuckUserRepository.findByUsername(p.getName()) && !pp.attendees.contains(potLuckUserRepository.findByUsername(p.getName()))){
            m.addAttribute("notcreator",false);
        }
        m.addAttribute("newPotluck",pp);
        return "potLuckDetail";

}

   @PostMapping("/Potlucks/{id}")
    public RedirectView updatePotluck(@PathVariable Long id, Principal p, Model m, @RequestParam String action){
            //find the potluck
       PotLuckUser cur = potLuckUserRepository.findByUsername(p.getName());
        if(action.equals("yes")) {
            PotLuck theone = potLuckRepository.findById(id).get();
            //add potluck to user's attending potlucks
            cur.attendingPotlucks.add(theone);
            theone.attendees.add(cur);
            potLuckRepository.save(theone);
            //add to database
            potLuckUserRepository.save(cur);
        }

      m.addAttribute("user",cur);

       return new RedirectView("/");
}

}

