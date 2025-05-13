package com.example.createAccountService.Repo;

import com.example.createAccountService.Entity.user_profiles;
import com.example.createAccountService.Entity.user_profilesMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface user_profilesRepoMongo extends MongoRepository<user_profilesMongo, String> {
}
