package com.someExample.social.entities;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Write something")
    @Length(max = 2000,message = "Post is too long")
    private String postText;
    @Length(max = 20,message = "Tag is too long")
    private String postTag;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;


    public Post (){

    }

    public Post(String postText, String postTag, User author) {
        this.postText = postText;
        this.postTag = postTag;
        this.author = author;
    }


    public String getAuthorName(){
        return author !=null ? author.getUsername() : "mr.anonymous";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String message) {
        this.postText = message;
    }

    public String getPostTag() {
        return postTag;
    }

    public void setPostTag(String title) {
        this.postTag = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
