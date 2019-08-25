package com.someExample.social.controllers;

import com.someExample.social.entities.User;
import com.someExample.social.enums.Roles;
import com.someExample.social.reps.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
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


    @GetMapping("{user}")
    public String editUser(@PathVariable User user, Map<String, Object> model){

        model.put("userToEdit", user);
        model.put("userRoles", Roles.values());
        return "editUser";
    }


    @PostMapping
    public String updateUser(
            @RequestParam Map<String,  String> form,
            @RequestParam String username,
            @RequestParam("userId") User user){

        user.setUsername(username);

        Set<String> roles = Arrays.stream(Roles.values()).map(Roles::name).collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Roles.valueOf(key));
            }
        }

        userRepo.save(user);
        return "redirect:/user";
    }


}
