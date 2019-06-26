package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Set;
@Transactional
@Controller
public class PotluckController {
    @Autowired
    PotluckRepository potLuckRepository;
    @Autowired
    PotluckUserRepository potLuckUserRepository;
    @Autowired
    PotluckItemRepository potLuckItemRepository;

    Potluck potluck;
    PotluckUser currentUser;

    @GetMapping("/Potlucks/{id}")
    public String getDetails(@PathVariable Long id, Principal P, Model model){

        potluck=potLuckRepository.findById(id).get();
        currentUser=potLuckUserRepository.findByUsername(P.getName());
        //check if the potluck belongs to the current user or the potluck's attendees contains the current user
      if(potluck.creator.equals(currentUser)){
          model.addAttribute("curruser",true);
      }

      else if(potluck.attendees.contains(currentUser))

      {
          model.addAttribute("curruser",false);
      }


        model.addAttribute("newPotluck",potluck);
        model.addAttribute("principal", P);
        return "potLuckDetail";
    }


    @PostMapping("/Potlucks/{id}")
    public RedirectView updatePotluck(@PathVariable Long id, Principal p, Model m, @RequestParam String action){
        //find the potluck
        currentUser = potLuckUserRepository.findByUsername(p.getName());
        if(action.equals("yes")) {
            potluck = potLuckRepository.findById(id).get();
            //add potluck to user's attending potlucks
            currentUser.attendingPotlucks.add(potluck);
            potluck.attendees.add(currentUser);
            potLuckRepository.save(potluck);
            //add to database
            potLuckUserRepository.save(currentUser);
        }

        m.addAttribute("user", currentUser);
        m.addAttribute("principal", p);
        return new RedirectView("/");
    }

    @GetMapping("/search")
    public String getOnepotluck(String code, Principal p, Model m){

        Potluck pp = potLuckRepository.findByCode(code);


        if(pp.creator!=potLuckUserRepository.findByUsername(p.getName()) && !pp.attendees.contains(potLuckUserRepository.findByUsername(p.getName()))){
            m.addAttribute("notcreator",false);
        }
        m.addAttribute("newPotluck",pp);
        m.addAttribute("principal", p);
        return "potLuckDetail";
    }

    @PostMapping("/addItem")
    public RedirectView addPotLuckItem(String itemName, int quantity, Principal p) {
        currentUser = potLuckUserRepository.findByUsername(p.getName());
        //create item and connect to user, save item to db
        PotluckItem itemToAdd = new PotluckItem(itemName, quantity,potluck,
                currentUser);
        //add to current user's list of items and update/save user to db
        potLuckItemRepository.save(itemToAdd);

        //add to potluck and update db
        potluck.attendees.add(currentUser);
        potluck.potluckItemList.add(itemToAdd);
        potLuckRepository.save(potluck);

        return new RedirectView("/Potlucks/" + potluck.id);
    }
   @PostMapping("/delete/{id}")
    public RedirectView deletePotluck(Principal p,@PathVariable Long id){
        potluck=potLuckRepository.findById(id).get();
         List<PotluckItem> list = potLuckItemRepository.findByPotluck(potluck);
        potLuckItemRepository.deleteAll(list);
        potluck.attendees.clear();
        potLuckRepository.deleteById(id);
        return new RedirectView("/");
    }
}

