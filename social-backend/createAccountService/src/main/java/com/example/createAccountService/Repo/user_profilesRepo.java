package com.example.createAccountService.Repo;

import com.example.createAccountService.Entity.user_profiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface user_profilesRepo extends JpaRepository<user_profiles, Integer> {
}
