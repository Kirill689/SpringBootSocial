package com.someExample.social.controllers;

import com.someExample.social.entities.User;
import com.someExample.social.enums.Role;
import com.someExample.social.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String users(Map<String, Object> model){

        model.put("allUsers", userService.findAll());
        return "usersList";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String editUser(@PathVariable User user, Map<String, Object> model){

        model.put("userToEdit", user);
        model.put("userRoles", Role.values());
        return "editUser";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String updateUser(
            @RequestParam Map<String,  String> form,
            @RequestParam String username,
            @RequestParam("userId") User user){

        userService.saveUser(user, username, form);
        return "redirect:/user";
    }


    @GetMapping("/profile")
    public String getProfile(Map<String, Object> model, @AuthenticationPrincipal User user){

        model.put("username", user.getUsername());
        model.put("email", user.getEmail());

        return "profile";
    }



    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user, @RequestParam String email, @RequestParam String password){

        userService.updateProfile(user, email, password);

        return "redirect:/user/profile";
    }



}
