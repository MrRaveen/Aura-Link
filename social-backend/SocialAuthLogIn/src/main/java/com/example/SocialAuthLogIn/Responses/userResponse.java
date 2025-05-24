package com.example.SocialAuthLogIn.Responses;

import jakarta.persistence.*;

import java.time.LocalTime;

public class userResponse {
    private int user_id;
    private String username;
    private String email;
    private String password_hash;
    private LocalTime created_at;

    public userResponse(String username, String email, String password_hash, LocalTime created_at) {
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
        this.created_at = created_at;
    }

    public userResponse() {

    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public LocalTime getCreated_at() {
        return created_at;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public void setCreated_at(LocalTime created_at) {
        this.created_at = created_at;
    }
}


