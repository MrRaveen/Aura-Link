package com.example.savePost2.Entity;

import com.example.savePost2.Entity.Posts;
import com.example.savePost2.Entity.users;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "SavedPosts")
public class SavedPosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "savedId")
    private int savedId;
    @Column(name = "date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private users user_id;
    @ManyToOne
    @JoinColumn(name = "postid",nullable = false)
    private Posts post;

    public SavedPosts() {
    }

    public SavedPosts(LocalDate date, users user_id, Posts post) {
        this.date = date;
        this.user_id = user_id;
        this.post = post;
    }

    public int getSavedId() {
        return savedId;
    }

    public LocalDate getDate() {
        return date;
    }

    public users getUser_id() {
        return user_id;
    }

    public Posts getPost() {
        return post;
    }

    public void setSavedId(int savedId) {
        this.savedId = savedId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setUser_id(users user_id) {
        this.user_id = user_id;
    }

    public void setPost(Posts post) {
        this.post = post;
    }
}
