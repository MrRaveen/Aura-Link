package com.example.createAccountService.Repo;

import com.example.createAccountService.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepo extends JpaRepository<users, Integer>{
}
