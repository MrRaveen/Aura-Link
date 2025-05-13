package com.example.savePost2.Repo;

import com.example.savePost2.Entity.PostComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentsRepo extends JpaRepository<PostComments, Integer> {
}
