package com.someExample.social.controllers;


import com.someExample.social.entities.Post;
import com.someExample.social.entities.User;
import com.someExample.social.reps.PostsRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    private PostsRepo postsRepo;

    public MainController (PostsRepo postsRepo){
        this.postsRepo = postsRepo;
    }



    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        return "index";
    }



    @GetMapping("/main")
    public String mainPage(@RequestParam(required = false) String filter, Map<String, Object> model){

        Iterable<Post> allPosts;

        if(filter!=null && !filter.isEmpty()){
            allPosts = postsRepo.findByPostTag(filter);
        }
        else {
            allPosts = postsRepo.findAll();
        }

        model.put("posts", allPosts);
        return "main";
    }



    @PostMapping("/main")
    public String makeSomePost(Map<String, Object> model, @RequestParam String postText, @RequestParam String postTag, @AuthenticationPrincipal User user){

        Post post = new Post(postText, postTag, user);
        postsRepo.save(post);

        Iterable<Post> allPosts = postsRepo.findAll();
        model.put("posts", allPosts);
        return "main";
    }


}
