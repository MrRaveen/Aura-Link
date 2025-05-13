package com.example.UserProfile.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalTime;

@Entity
@Table(name = "user_profiles")
public class User_profiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    private String bio;

    @Column(name = "profile_pic_url")
    private String profile_pic_url;

    @JsonProperty("lat_location")  // Map JSON key "lat_location" to this field
    @Column(name = "latLocation")
    private String latLocation;

    @JsonProperty("lon_location")  // Map JSON key "lon_location" to this field
    @Column(name = "lonLocation")
    private String lonLocation;

    @Column(name = "birth_date")
    private Timestamp birth_date;  // Fixed typo (was birdth_date)

    @Column(name = "joined_at")
    private LocalTime joined_at;

    private int age;

    private int mobile;

    private String address;

    @JsonProperty("job")  // Map JSON key "job" to this field
    @Column(name = "Job")
    private String Job;

    // No-arg constructor (required for Jackson/JPA)
    public User_profiles() {}

    // Constructor with all fields
    public User_profiles(Integer user_id, String first_name, String last_name, String bio,
                         String profile_pic_url, String latLocation, String lonLocation,
                         Timestamp birth_date, LocalTime joined_at, int age, int mobile,
                         String address, String job) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.bio = bio;
        this.profile_pic_url = profile_pic_url;
        this.latLocation = latLocation;
        this.lonLocation = lonLocation;
        this.birth_date = birth_date;
        this.joined_at = joined_at;
        this.age = age;
        this.mobile = mobile;
        this.address = address;
        this.Job = job;
    }

    // Getters and Setters (ensure they match corrected field names)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Integer getUser_id() { return user_id; }
    public void setUser_id(Integer user_id) { this.user_id = user_id; }

    public String getFirst_name() { return first_name; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }

    public String getLast_name() { return last_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getProfile_pic_url() { return profile_pic_url; }
    public void setProfile_pic_url(String profile_pic_url) { this.profile_pic_url = profile_pic_url; }

    public String getLatLocation() { return latLocation; }
    public void setLatLocation(String latLocation) { this.latLocation = latLocation; }

    public String getLonLocation() { return lonLocation; }
    public void setLonLocation(String lonLocation) { this.lonLocation = lonLocation; }

    public Timestamp getBirth_date() { return birth_date; }  // Fixed getter name
    public void setBirth_date(Timestamp birth_date) { this.birth_date = birth_date; }

    public LocalTime getJoined_at() { return joined_at; }
    public void setJoined_at(LocalTime joined_at) { this.joined_at = joined_at; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getMobile() { return mobile; }
    public void setMobile(int mobile) { this.mobile = mobile; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getJob() { return Job; }
    public void setJob(String job) { this.Job = job; }
}