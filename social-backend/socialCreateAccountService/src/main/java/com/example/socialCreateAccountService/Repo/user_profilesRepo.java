package com.example.socialCreateAccountService.Repo;

import com.example.socialCreateAccountService.Entity.user_profiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface user_profilesRepo extends JpaRepository<user_profiles, Integer> {
}
