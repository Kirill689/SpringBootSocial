package com.someExample.social.controllers;


import com.someExample.social.entities.Dto.CaptchaResponceDto;
import com.someExample.social.entities.User;
import com.someExample.social.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class Registration {


    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private UserService userService;

    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    public Registration(UserService userService, RestTemplate restTemplate){
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(@RequestParam("g-recaptcha-response") String gRecaptchaResponce, @Valid User user, BindingResult bindingResult , Map<String, Object> model){

        String url = String.format(CAPTCHA_URL, recaptchaSecret, gRecaptchaResponce);
        CaptchaResponceDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponceDto.class);
        System.out.println(gRecaptchaResponce);

        if(!response.isSuccess()){
            model.put("captchaError", "Fill captcha please!");
        }

        if(bindingResult.hasErrors()){
            Map<String, String> errorsList = ControllersUtil.getErrorsList(bindingResult);
            model.putAll(errorsList);

            return "registration";
        }

        if(!response.isSuccess()){

            Map<String, String> errors = ControllersUtil.getErrorsList(bindingResult);

            model.putAll(errors);
            return "registration";

        }

        if (!userService.addUser(user)){
            model.put("usernameError", "User already exists");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Map<String, Object> model, @PathVariable String code){

        boolean isActivated = userService.activateUser(code);

        System.out.println(isActivated);

        if(isActivated){
            model.put("messageType", "success");
            model.put("message", "User account is active now");
        }
        else {
            model.put("messageType", "danger");
            model.put("message", "User activation error");
        }

        return "redirect:/login";
    }

}
