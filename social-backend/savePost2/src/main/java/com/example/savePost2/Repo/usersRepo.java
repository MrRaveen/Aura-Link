package com.example.savePost2.Repo;

import com.example.savePost2.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepo extends JpaRepository<users, Integer> {
}
