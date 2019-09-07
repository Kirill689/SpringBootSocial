package com.someExample.social.controllers;


import com.someExample.social.entities.Post;
import com.someExample.social.entities.User;
import com.someExample.social.reps.PostsRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    private PostsRepo postsRepo;

    public MainController (PostsRepo postsRepo){
        this.postsRepo = postsRepo;
    }


    @Value("${upload.path}")
    private String uploadPath;


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
    public String makeSomePost(Map<String, Object> model,
                               @RequestParam String postText,
                               @RequestParam String postTag,
                               @AuthenticationPrincipal User user,
                               @RequestParam (required=false, defaultValue = "" ) MultipartFile file) throws IOException {

        Post post = new Post(postText, postTag, user);

        if(file!=null && !file.getOriginalFilename().isEmpty()){

            File upload = new File(uploadPath);

            if (!upload.exists()){
                upload.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String filename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath  + "/" + filename));

            post.setFilename(filename);
        }

        postsRepo.save(post);

        Iterable<Post> allPosts = postsRepo.findAll();
        model.put("posts", allPosts);
        return "main";
    }


}
