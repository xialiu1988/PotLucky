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

/**
 * This is the Controller
 * for a PotluckUser.
 */
@Controller
public class PotluckUserController {
    @Autowired
    private PotluckUserRepository potLuckUserRepository;

    @Autowired
    private PotluckRepository potLuckRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Get mapping to retrieve registration page
     * for new user.
     * @return String, html page
     */
    @GetMapping("/signup")
    public String getsignup() {
        return "signup";
    }


    /**
     * Post mapping to save
     * information of new user.
     * @param firstname String, user first name
     * @param lastname String, user last name
     * @param username String, user username (must be unique)
     * @param password String, user password
     * @return View, html to access logged-in home
     */
    @PostMapping("/signup")
    public RedirectView addNewUser(String firstname, String lastname, String username, String password) {
        PotluckUser newUser = new PotluckUser(firstname, lastname, username, passwordEncoder.encode(password));
        potLuckUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/myprofile");
    }

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








}