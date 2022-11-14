package com.valerio.user_registration.controller;

import com.valerio.user_registration.dao.UserRepo;
import com.valerio.user_registration.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    @Autowired
    private UserRepo userRepo;

    @GetMapping("")
    public String HomePage(){
        return "index";
    }

    @GetMapping("/users")
    public String UserPage(){
        return "users";
    }
    @GetMapping("/register")
    public String RegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register_action")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "success";
    }
}
