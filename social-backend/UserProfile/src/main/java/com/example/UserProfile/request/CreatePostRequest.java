package com.example.UserProfile.request;

import com.example.UserProfile.entity.Users;
import com.example.UserProfile.entity.postEntity;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalTime;

public class CreatePostRequest {
    public enum content_type{
        VIDEO,
        IMAGE,
        TEXT,
        VIDEO_IMAGES
    }
    private postEntity.content_type content_type;
    private Date date;
    private String description;
    private LocalTime time;
    private String title;
    private int user_id;

    public CreatePostRequest() {
    }

    public void setContent_type(postEntity.content_type content_type) {
        this.content_type = content_type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public postEntity.content_type getContent_type() {
        return content_type;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public int getUser_id() {
        return user_id;
    }

    public CreatePostRequest(postEntity.content_type content_type, Date date, String description, LocalTime time, String title, int user_id) {
        this.content_type = content_type;
        this.date = date;
        this.description = description;
        this.time = time;
        this.title = title;
        this.user_id = user_id;
    }
}
