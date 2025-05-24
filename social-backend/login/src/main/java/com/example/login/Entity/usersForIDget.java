package com.example.login.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Time;
import java.sql.Timestamp;

public class usersForIDget {
    @JsonProperty("user_id")
    private int user_id;

    @JsonProperty("created_at")
    private String created_at;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password_hash")
    private String password_hash;

    @JsonProperty("username")
    private String username;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public String getUsername() {
        return username;
    }

    public usersForIDget() {
    }

    public usersForIDget(int user_id, String created_at, String email, String password_hash, String username) {
        this.user_id = user_id;
        this.created_at = created_at;
        this.email = email;
        this.password_hash = password_hash;
        this.username = username;
    }
}
