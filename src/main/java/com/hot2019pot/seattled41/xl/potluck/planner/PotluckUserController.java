package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


import java.security.Principal;
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
     * Landing page for logged-in user personal splash page.
     * @param p Principal, logged-in user
     * @param m Model, cache/hive logged-in user
     * @return String, html page to retrieve
     */
    @GetMapping("/myprofile")
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





}