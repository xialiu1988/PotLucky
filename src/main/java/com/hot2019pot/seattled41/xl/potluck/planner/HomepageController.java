package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Controller for website
 */
@Controller
public class HomepageController {

    @Autowired
    PotluckUserRepository potLuckUserRepository;


    /**
     * Login page.
     * @return String, html page to retrieve
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }


    /**
     * Get mapping for website splash/landing page.
     * @param m Model, hive/cache for front-facing
     * @param p Principal, logged-in user object
     * @return String, page to retrieve
     */
    @GetMapping("/")
    public String goHomePage(Model m, Principal p){
        if (p ==null) {
            m.addAttribute("principal", null);
        }else {
            m.addAttribute("principal", p);
        }
        return "home";
    }

    /**
     * Get mapping for logout
     * @return String, html page to retrive
     */
    @GetMapping("/logout_complete")
    public String getLogoutPage() {
        return "logout_completed.html";
    }

    /**
     * Get mapping for about us page.
     * @param m Model, hive/cache for front-facing
     * @param p Principal, logged-in user
     * @return String, html page to retrieve
     */
    @GetMapping("/aboutus")
    public String getAboutUs(Model m, Principal p) {
        if (p ==null) {
            m.addAttribute("principal", null);
        }else {
            m.addAttribute("principal", p);
        }
        return "aboutus.html";
    }

}
