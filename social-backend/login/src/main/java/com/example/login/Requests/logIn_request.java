package com.example.login.Requests;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "users")
public class logIn_request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "username")
    private String username;
    @Column(name = "password_hash")
    private String password_hash;

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

//    public String getEmail() {
//        return email;
//    }

    public String getPassword_hash() {
        return password_hash;
    }

//    public Time getCreated_at() {
//        return created_at;
//    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

//    public void setCreated_at(Time created_at) {
//        this.created_at = created_at;
//    }
}
