package com.example.createAccountService.Requests;

public class UserTempRequest {
    private usersMongoRequest user;
    private user_profilesMongoRequest profile;

    // Constructor
    public UserTempRequest() {}

    public UserTempRequest(usersMongoRequest user, user_profilesMongoRequest profile) {
        this.user = user;
        this.profile = profile;
    }

    // Getters and Setters
    public usersMongoRequest getUser() {
        return user;
    }

    public void setUser(usersMongoRequest user) {
        this.user = user;
    }

    public user_profilesMongoRequest getProfile() {
        return profile;
    }

    public void setProfile(user_profilesMongoRequest profile) {
        this.profile = profile;
    }
}

