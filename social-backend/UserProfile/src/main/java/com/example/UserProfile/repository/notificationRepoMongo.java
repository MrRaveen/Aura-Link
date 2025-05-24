package com.example.UserProfile.repository;

import com.example.UserProfile.entity.notificationsMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface notificationRepoMongo extends MongoRepository<notificationsMongo, Integer> {
}
