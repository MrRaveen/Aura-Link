package com.example.UserProfile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.UserProfile.entity.CheckEmailVerificationMongo;

public interface CheckEmailVerificationMongoRepo extends MongoRepository<CheckEmailVerificationMongo, String>{

}
