package com.example.savePost2.Repo;

import com.example.savePost2.Entity.SavedPosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedPostsRepo extends JpaRepository<SavedPosts, Integer> {
}
