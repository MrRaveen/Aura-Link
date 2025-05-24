package com.example.login.Repo;

import com.example.login.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepo extends JpaRepository<users, Integer> {
}