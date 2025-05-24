package com.example.UserProfile.response;

import java.util.Date;

public class OtherInfoRes {
    private String Job;
    private String bio;
    private Date DOB;
    private String fname;
    private String lname;
    private String profile_pic_url;
    private String email;
    private String followersCount;
    private String postCount;
    private String followingCount;

    public void setJob(String job) {
        Job = job;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFollowersCount(String followersCount) {
        this.followersCount = followersCount;
    }

    public void setPostCount(String postCount) {
        this.postCount = postCount;
    }

    public void setFollowingCount(String followingCount) {
        this.followingCount = followingCount;
    }

    public String getJob() {
        return Job;
    }

    public String getBio() {
        return bio;
    }

    public Date getDOB() {
        return DOB;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public String getEmail() {
        return email;
    }

    public String getFollowersCount() {
        return followersCount;
    }

    public String getPostCount() {
        return postCount;
    }

    public String getFollowingCount() {
        return followingCount;
    }

    public OtherInfoRes(String job, String bio, Date DOB, String fname, String lname, String profile_pic_url, String email, String followersCount, String postCount, String followingCount) {
        Job = job;
        this.bio = bio;
        this.DOB = DOB;
        this.fname = fname;
        this.lname = lname;
        this.profile_pic_url = profile_pic_url;
        this.email = email;
        this.followersCount = followersCount;
        this.postCount = postCount;
        this.followingCount = followingCount;
    }
}
