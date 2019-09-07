package com.someExample.social.controllers;


import com.someExample.social.entities.User;
import com.someExample.social.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class Registration {


    private UserService userService;

    public Registration(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(Map<String, Object> model, User user){

        if (!userService.addUser(user)){
            model.put("MSG", "User already exists");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Map<String, Object> model, @PathVariable String code){

        boolean isActivated = userService.activateUser(code);

        if(isActivated){
            model.put("MSG", "User account is active now");
        }
        else {
            model.put("MSG", "User activation error");
        }

        return "login";
    }

}
