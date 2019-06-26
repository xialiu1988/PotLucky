package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

/**
 * This is the Controller
 * for Potluck routes.
 * Has create, delete, update, add routes
 * and get for a specific Potluck.
 */
@Controller
public class PotluckController {
    @Autowired
    PotluckRepository potLuckRepository;
    @Autowired
    PotluckUserRepository potLuckUserRepository;
    @Autowired
    PotluckItemRepository potLuckItemRepository;

    private Potluck potluck;
    private PotluckUser currentUser;

    /**
     * Get route for one Potluck object.
     * @param id long, Potluck id
     * @param P Principal object, logged-in user
     * @param model Model, hive/cache for front-facing
     * @return String, html page to view one Potluck
     */
    @GetMapping("/Potlucks/{id}")
    public String getDetails(@PathVariable Long id, Principal P, Model model){

        potluck=potLuckRepository.findById(id).get();
        currentUser=potLuckUserRepository.findByUsername(P.getName());
        //check if the potluck belongs to the current user or the potluck's attendees contains the current user
      if(potluck.creator.equals(currentUser)){
          model.addAttribute("curruser",true);
      } else if(potluck.attendees.contains(currentUser)) {
          model.addAttribute("curruser",false);
      }

        model.addAttribute("newPotluck",potluck);
        model.addAttribute("principal", P);
        return "potLuckDetail";
    }

    /**
     * Post route for Potluck.
     * @param id long, Potluck id
     * @param p Principal object, logged-in user
     * @param m Model, hive/cache for front-facing
     * @param action String, if action is yes, goes into conditional
     *               to update
     * @return
     */
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

    /**
     * Get route for db search for one Potluck
     * that already exists, that another User created.
     * @param code String, code to search for existing Potluck
     * @param p Principal object, logged-in user
     * @param m Model, hive/cache for front-facing
     * @return String, html page to view the Potluck
     */
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

    /**
     * Post mapping to add a PotluckItem.
     * @param itemName String, item
     * @param quantity int, quantity of item
     * @param p Principal, logged-in user
     * @return String, html page to view the revised Potluck
     */
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

    /**
     * Post mapping to delete a Potluck object.
     * @param p Principal, logged-in user
     * @param id long, id of Potluck object
     * @return View, route to html page to view revised Potluck
     */
    @PostMapping("/delete/{id}")
    public RedirectView deletePotluck(Principal p, @PathVariable Long id){
        potluck=potLuckRepository.findById(id).get();
         List<PotluckItem> list = potLuckItemRepository.findByPotluck(potluck);
        potLuckItemRepository.deleteAll(list);
        potluck.attendees.clear();
        potLuckRepository.deleteById(id);
        return new RedirectView("/");
    }

    /**
     * Post mapping to delete a PotluckItem object.
     * @param id long, id of PotluckItem
     * @return View, route to html page to view revised Potluck
     */
    @PostMapping("/delete/potluckitems/{id}")
    public RedirectView deletePotluckItem(@PathVariable Long id){

        potLuckItemRepository.deleteById(id);
        return new RedirectView("/Potlucks/" + potluck.id);
    }

    /**
     * Post mapping to update details of a Potluck object.
     * Only creator could see and access.
     * @param details String, Potluck additional details
     * @param location String, Potluck location
     * @param dateofPotluck Date, Potluck date
     * @return View, route to html page to view revised Potluck
     */
    @PostMapping("/updateDetails")
    public RedirectView updatePotluckDetails(String details, String location, Date dateofPotluck) {
        //reset details and save to db
        potluck.setLocation(location);
        potluck.setDetails(details);
        potluck.setDateofPotluck(dateofPotluck);
        potLuckRepository.save(potluck);
        return new RedirectView("/Potlucks/" + potluck.id);
    }

}

