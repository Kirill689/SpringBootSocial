package com.someExample.social.controllers;


import com.someExample.social.entities.User;
import com.someExample.social.enums.Role;
import com.someExample.social.reps.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class Registration {

    private UserRepo userRepo;

    public Registration(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(Map<String, Object> model, User user){

        User newUser = userRepo.findByUsername(user.getUsername());

        if (newUser!=null){
            model.put("MSG", "User already exists");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }

}
