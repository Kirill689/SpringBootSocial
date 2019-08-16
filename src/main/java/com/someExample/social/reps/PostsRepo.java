package com.someExample.social.reps;

import com.someExample.social.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepo extends JpaRepository<Post, Long> {

    List<Post> findByPostTag(String postTag);

}
