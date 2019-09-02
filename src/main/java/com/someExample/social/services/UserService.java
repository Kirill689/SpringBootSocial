package com.someExample.social.services;


import com.someExample.social.reps.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println( userRepo.findByUsername(username).getRoles());
        System.out.println( userRepo.findByUsername(username).getUsername());
        System.out.println( userRepo.findByUsername(username).getAuthorities());
        System.out.println( userRepo.findByUsername(username).getId());
        System.out.println( userRepo.findByUsername(username).getPassword());
        return userRepo.findByUsername(username);
    }
}
