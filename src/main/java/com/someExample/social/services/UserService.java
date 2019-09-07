package com.someExample.social.services;


import com.someExample.social.entities.User;
import com.someExample.social.enums.Role;
import com.someExample.social.reps.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private MailService mailService;

    public UserService(UserRepo userRepo, MailService mailService) {
        this.userRepo = userRepo;
        this.mailService = mailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }


    public boolean addUser(User user){

        User newUser = userRepo.findByUsername(user.getUsername());

        if (newUser!=null){
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())){

            String message =  String.format(
                    "Hello: %s! \n" +
                    "Please visit this link: http://localhost/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailService.sendMail(user.getEmail(), "Activation link", message);

        }
            return true;
    }

    public boolean activateUser(String code) {

        User user = userRepo.findByActivationCode(code);

        if(user == null){
            return false;
        }

        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }
}
