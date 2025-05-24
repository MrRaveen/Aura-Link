package com.example.socialCreateAccountService.Repo;

import com.example.socialCreateAccountService.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usersRepo extends JpaRepository<users, Integer>{
}
