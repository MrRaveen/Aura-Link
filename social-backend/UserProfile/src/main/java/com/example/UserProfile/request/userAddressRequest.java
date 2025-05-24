package com.example.UserProfile.request;

public class userAddressRequest {
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

    public userAddressRequest() {
    }

    public userAddressRequest(String id, String addressLong) {
        this.id = id;
        this.addressLong = addressLong;
    }
}
