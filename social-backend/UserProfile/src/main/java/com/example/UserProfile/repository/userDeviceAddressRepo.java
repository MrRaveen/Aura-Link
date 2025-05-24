package com.example.UserProfile.repository;

import com.example.UserProfile.entity.userDeviceAddress;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userDeviceAddressRepo extends MongoRepository<userDeviceAddress, String> {
}
