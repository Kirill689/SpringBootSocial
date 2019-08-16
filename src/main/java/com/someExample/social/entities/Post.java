package com.someExample.social.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String postText;

    private String postTag;




    public Post (){

    }

    public Post(Integer id, String postText, String postTag) {
        this.id = id;
        this.postText = postText;
        this.postTag = postTag;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
