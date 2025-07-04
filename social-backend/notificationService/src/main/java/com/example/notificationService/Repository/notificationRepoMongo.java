package com.example.notificationService.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.notificationService.Entity.notificationsMongo;

public interface notificationRepoMongo extends MongoRepository<notificationsMongo, Integer>{
	List<notificationsMongo> findByRecipientUserId(int recipientUserId);
}
