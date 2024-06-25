package com.awbd.controllers;

import com.awbd.entities.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping({"","/","/home"})
    public ModelAndView getHome(){
        return new ModelAndView("main");
    }


    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String processSignUpForm(@ModelAttribute("user") Users user){


        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogInForm(){ return "login"; }

    @GetMapping("/access_denied")
    public String accessDeniedPage(){ return "accessDenied"; }
}
