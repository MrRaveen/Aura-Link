package com.example.createAccountService.Repo;

import com.example.createAccountService.Entity.users;
import com.example.createAccountService.Entity.usersMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface usersRepoMongo extends MongoRepository <usersMongo,String>{

}
