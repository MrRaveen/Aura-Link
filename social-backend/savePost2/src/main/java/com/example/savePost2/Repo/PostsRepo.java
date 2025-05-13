package com.example.savePost2.Repo;

import com.example.savePost2.Entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepo extends JpaRepository<Posts, Integer> {
}
