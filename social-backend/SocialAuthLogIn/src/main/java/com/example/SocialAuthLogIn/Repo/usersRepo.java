package com.example.SocialAuthLogIn.Repo;

import com.example.SocialAuthLogIn.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface usersRepo extends JpaRepository<users, Integer> {
    //to test
    @Query(value = "SELECT u FROM users u")
    public List<users> testQuery();
}
