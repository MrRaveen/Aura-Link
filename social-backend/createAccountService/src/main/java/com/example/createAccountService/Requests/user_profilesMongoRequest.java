package com.example.createAccountService.Requests;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class user_profilesMongoRequest {
    private String first_name;
    private String last_name;
    private String bio;
    private String profile_pic_url;
    private String latLocation;
    private String lonLocation;
    private Date birdth_date;
    private LocalTime joined_at;
    private int age;
    private int mobile;
    private String address;
    private String job;

    public user_profilesMongoRequest(
            String first_name, String last_name, String bio, String profile_pic_url, String latLocation,
            String lonLocation, Date birdth_date, LocalTime joined_at,
            int age, int mobile, String address, String job) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.bio = bio;
        this.profile_pic_url = profile_pic_url;
        this.latLocation = latLocation;
        this.lonLocation = lonLocation;
        this.birdth_date = birdth_date;
        this.joined_at = joined_at;
        this.age = age;
        this.mobile = mobile;
        this.address = address;
        this.job = job;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    public void setLatLocation(String latLocation) {
        this.latLocation = latLocation;
    }

    public void setLonLocation(String lonLocation) {
        this.lonLocation = lonLocation;
    }

    public void setBirdth_date(Date birdth_date) {
        this.birdth_date = birdth_date;
    }

    public void setJoined_at(LocalTime joined_at) {
        this.joined_at = joined_at;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getBio() {
        return bio;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public String getLatLocation() {
        return latLocation;
    }

    public String getLonLocation() {
        return lonLocation;
    }

    public Date getBirdth_date() {
        return birdth_date;
    }

    public LocalTime getJoined_at() {
        return joined_at;
    }

    public int getAge() {
        return age;
    }

    public int getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getJob() {
        return job;
    }
}
