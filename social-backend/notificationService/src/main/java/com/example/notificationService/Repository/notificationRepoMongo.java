package com.example.notificationService.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.notificationService.Entity.notificationsMongo;

public interface notificationRepoMongo extends MongoRepository<notificationsMongo, Integer>{
}
