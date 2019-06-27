package com.hot2019pot.seattled41.xl.potluck.planner;

import com.google.gson.Gson;
import com.hot2019pot.seattled41.xl.potluck.planner.Map.MyPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;


import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;


@Controller
public class PotluckUserController {
    @Value("${GEOCODE_API_KEY}")
    private String apikey;
    @Autowired
    PotluckUserRepository potLuckUserRepository;

    @Autowired
    PotluckRepository potLuckRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/signup")
    public String getsignup() {
        return "signup";
    }


    @PostMapping("/signup")
    public RedirectView addNewUser(String firstname, String lastname, String username, String password) {
        PotluckUser newUser = new PotluckUser(firstname, lastname, username, passwordEncoder.encode(password));
        potLuckUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }


    @GetMapping("/Potluck/add")
    public String createPotluck(Model m, Principal p) {
        m.addAttribute("principal", p);
        return "createPotluck";
    }

    @GetMapping("/home")
    public String goHomePage(Model m, Principal p) {
        if (p == null) {
            m.addAttribute("principal", null);
        } else {
            m.addAttribute("principal", p);
        }
        return "home.html";
    }

    @GetMapping("/logout_complete")
    public String getLogoutPage() {
        return "logout_completed.html";
    }

    @GetMapping("/aboutus")
    public String getAboutUs(Model m, Principal p) {
        if (p == null) {
            m.addAttribute("principal", null);
        } else {
            m.addAttribute("principal", p);
        }
        return "aboutus.html";
    }

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
      double[] location1 = getLocaionMap(location);
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
    private double[] getLocaionMap(String query) {
       double[] results=new double[2];
        MyPojo location = new MyPojo();
        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address="+query+"&key="+apikey;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(apiUrl, String.class);
        System.out.println(result);
        Gson g = new Gson();
        location = g.fromJson(result, MyPojo.class);
        System.out.println(location.getResults().get(0).getGeometry().getLocation().getLat());
        System.out.println(location.getResults().get(0).getGeometry().getLocation().getLng());
        results[0]=location.getResults().get(0).getGeometry().getLocation().getLat();
        results[1]=location.getResults().get(0).getGeometry().getLocation().getLng();
        return results;
    }

}