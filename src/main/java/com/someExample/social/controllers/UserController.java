package com.someExample.social.controllers;

import com.someExample.social.entities.User;
import com.someExample.social.reps.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }



    @GetMapping
    public String users(Map<String, Object> model){

        model.put("allUsers", userRepo.findAll());
        return "usersList";
    }


    @GetMapping("{id}")
    public String editUser(@PathVariable User user, Map<String, Object> model){

        model.put("userToEdit", user);
        return "editUser";
    }




}
