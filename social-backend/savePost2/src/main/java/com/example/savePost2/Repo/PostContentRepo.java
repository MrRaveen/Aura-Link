package com.example.savePost2.Repo;

import com.example.savePost2.Entity.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostContentRepo extends JpaRepository<PostContent, Integer> {
}
