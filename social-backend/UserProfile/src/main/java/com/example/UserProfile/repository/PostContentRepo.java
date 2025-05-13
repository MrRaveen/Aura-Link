package com.example.UserProfile.repository;

import com.example.UserProfile.entity.PostContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostContentRepo extends JpaRepository<PostContentEntity, Integer> {
}
