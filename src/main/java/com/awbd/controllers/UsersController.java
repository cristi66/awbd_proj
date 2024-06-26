package com.awbd.controllers;

import com.awbd.dtos.UsersDTO;
import com.awbd.entities.Users;
import com.awbd.services.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {

    UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String processSignUpForm(@ModelAttribute UsersDTO user){
        usersService.findByEmail(user.getEmail());
        usersService.save(user);
        return "redirect:/login";
    }

//    @PostMapping("")
//    public String addUser(@ModelAttribute UsersDTO user) {
//        usersService.save(user);
//        return "redirect:/login";
//    }
}
