package com.example.UserProfile.entity;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("userDeviceAddress")
public class userDeviceAddress {
    @Id
    private String id;
    private String addressLong;

    public void setId(String id) {
        this.id = id;
    }

    public void setAddressLong(String addressLong) {
        this.addressLong = addressLong;
    }

    public String getId() {
        return id;
    }

    public String getAddressLong() {
        return addressLong;
    }

    public userDeviceAddress() {
    }

    public userDeviceAddress(String id, String addressLong) {
        this.id = id;
        this.addressLong = addressLong;
    }
}
