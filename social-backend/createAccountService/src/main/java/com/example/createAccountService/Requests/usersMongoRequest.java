package com.example.createAccountService.Requests;
import java.time.LocalTime;

public class usersMongoRequest {
    private String username;
    private String email;
    private String password_hash;
    private LocalTime created_at;

    public usersMongoRequest(String username, String email, String password_hash, LocalTime created_at) {
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
        this.created_at = created_at;
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
