package com.example.savePost2.Request;

import com.example.savePost2.Entity.users;

import java.time.LocalDate;
import java.time.LocalTime;

public class PostRequest {
    public enum ContentType{
        VIDEO,
        IMAGE,
        TEXT,
        VIDEO_IMAGES
    }
    private int postid;
    private ContentType contentType;
    private LocalDate date;
    private LocalTime time;
    private String title;
    private String description;
    private users user_id;

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

    public PostRequest() {
    }

    public PostRequest(ContentType contentType, LocalDate date, LocalTime time, String title, String description, users user_id) {
        this.contentType = contentType;
        this.date = date;
        this.time = time;
        this.title = title;
        this.description = description;
        this.user_id = user_id;
    }
}
