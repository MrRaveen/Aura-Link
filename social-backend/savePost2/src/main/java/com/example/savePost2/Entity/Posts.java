package com.example.savePost2.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="Posts")
public class Posts {
    public enum ContentType{
        VIDEO,
        IMAGE,
        TEXT,
        VIDEO_IMAGES
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postid")
    private int postid;
    @Enumerated(EnumType.STRING)
    @Column(name = "contentType")
    private ContentType contentType;
    @Column(name="date")
    private LocalDate date;
    @Column(name="time")
    private LocalTime time;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private users user_id;

    public Posts() {
    }

    public Posts(ContentType contentType, LocalDate date, LocalTime time, String title, String description, users user_id) {
        this.contentType = contentType;
        this.date = date;
        this.time = time;
        this.title = title;
        this.description = description;
        this.user_id = user_id;
    }

    public int getPostid() {
        return postid;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public users getUser_id() {
        return user_id;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser_id(users user_id) {
        this.user_id = user_id;
    }
}
