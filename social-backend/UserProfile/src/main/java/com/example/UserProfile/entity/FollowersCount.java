package com.example.UserProfile.entity;

public class FollowersCount {

    public void setFollowerscountNumber(String followerscountNumber) {
        this.followerscountNumber = followerscountNumber;
    }

    public String getFollowerscountNumber() {
        return followerscountNumber;
    }

    public FollowersCount(String followerscountNumber) {
        this.followerscountNumber = followerscountNumber;
    }

    private String followerscountNumber;
}
