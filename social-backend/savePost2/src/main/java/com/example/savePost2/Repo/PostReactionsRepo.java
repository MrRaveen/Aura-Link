package com.example.savePost2.Repo;

import com.example.savePost2.Entity.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReactionsRepo extends JpaRepository<PostReaction, Integer> {
}
