package com.hot2019pot.seattled41.xl.potluck.planner;

import com.google.gson.Gson;
import com.hot2019pot.seattled41.xl.potluck.planner.Map.GoogleApiObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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
    @Value("${GEOCODE_API_KEY}")
    private String apikey;
    @Autowired
    PotluckRepository potLuckRepository;
    @Autowired
    PotluckUserRepository potLuckUserRepository;
    @Autowired
    PotluckItemRepository potLuckItemRepository;

    private Potluck potluck;
    private PotluckUser currentUser;


    /**
     * Get mapping for user to route to
     * page to add a new Potluck.
     * @param m Model, hive/cache for front-facing
     * @param p Principal, logged-in user object
     * @return String, page to retrieve
     */
    @GetMapping("/Potluck/add")
    public String createPotluck(Model m, Principal p) {
        m.addAttribute("principal", p);
        return "createPotluck";
    }

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
        return new RedirectView("/myprofile");
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
        return new RedirectView("/myprofile");
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

        //get location
        double[] location1 = getLocationMap(location);
        potluck.setMapResult(location1);
        //reset details and save to db
        potluck.setLocation(location);
        potluck.setDetails(details);
        potluck.setDateofPotluck(dateofPotluck);
        potLuckRepository.save(potluck);
        return new RedirectView("/Potlucks/" + potluck.id);
    }

    /**
     * Post mapping to create new Potluck object
     * and save it to db.
     * @param p Principal, logged-in user
     * @param eventname String, Potluck name
     * @param dateofPotluck Date, Potluck date
     * @param location String, Potluck location
     * @param details String, Potluck additional details
     * @param m Model, hive/cache for front-facing
     * @return String, html page to retrieve
     */

    @PostMapping("/Potluck")
    public String newPotluck(Principal p, String eventname, Date dateofPotluck, String location, String details, Model m) {
        PotluckUser creator = potLuckUserRepository.findByUsername(p.getName());

        Potluck newP = new Potluck();
        newP.eventname = eventname;
        newP.dateofPotluck = dateofPotluck;
        newP.location = location;
        newP.details = details;
        newP.creator = creator;
        //get location
        double[] location1 = getLocationMap(location);
        //generate code
        String code = generateCode();
        newP.code = code;
        newP.mapResult=location1;
        potLuckRepository.save(newP);
        System.out.println(newP.mapResult[0]);
        Potluck find = potLuckRepository.findByCode(code);
        creator.createPotlucks.add(newP);
        potLuckUserRepository.save(creator);
        m.addAttribute("newPotluck", newP);
        return "redirect:/Potlucks/" + find.id;
    }


    private String generateCode() {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }


    //make google map api call store lag,lng to the result string array
    private double[] getLocationMap(String query) {
        double[] results=new double[2];
        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address="+query+"&key="+apikey;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(apiUrl, String.class);
        System.out.println(result);
        Gson g = new Gson();
        GoogleApiObject location = g.fromJson(result, GoogleApiObject.class);

        results[0]=location.getResults().get(0).getGeometry().getLocation().getLat();
        results[1]=location.getResults().get(0).getGeometry().getLocation().getLng();
        return results;
    }


}

