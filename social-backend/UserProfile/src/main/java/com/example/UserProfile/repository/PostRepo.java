package com.example.UserProfile.repository;
import com.example.UserProfile.entity.postEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<postEntity, Integer> {

}
