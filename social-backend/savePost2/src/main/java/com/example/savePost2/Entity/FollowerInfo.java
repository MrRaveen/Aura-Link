package com.example.savePost2.Entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "FollowerInfo")
public class FollowerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "followerID")
    private int followerID;
    @Column(name = "time")
    private LocalTime time;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private users user_id;
    @ManyToOne
    @JoinColumn(name = "follower",nullable = false)
    private users follower;

    public FollowerInfo(LocalTime time, users user_id, users follower) {
        this.time = time;
        this.user_id = user_id;
        this.follower = follower;
    }

    public FollowerInfo() {

    }

    public void setFollowerID(int followerID) {
        this.followerID = followerID;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setUser_id(users user_id) {
        this.user_id = user_id;
    }

    public void setFollower(users follower) {
        this.follower = follower;
    }

    public int getFollowerID() {
        return followerID;
    }

    public LocalTime getTime() {
        return time;
    }

    public users getUser_id() {
        return user_id;
    }

    public users getFollower() {
        return follower;
    }
}
