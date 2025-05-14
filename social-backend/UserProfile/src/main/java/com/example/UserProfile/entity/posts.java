package com.example.UserProfile.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;
public class posts implements Serializable {
    private static final long serialVersionUID = 1L;
    public enum content_type{
        VIDEO,
        IMAGE,
        TEXT,
        VIDEO_IMAGES
    }
    private int postid;
    @JsonProperty("content_type")
    private content_type contentType;
    private Date date;
    private String description;
    private LocalTime time;
    private String title;
    private int user_id;

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public void setContentType(content_type contentType) {
        this.contentType = contentType;
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

    public int getPostid() {
        return postid;
    }

    public content_type getContentType() {
        return contentType;
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

    public posts(int postid, content_type contentType, Date date, String description, LocalTime time, String title, int user_id) {
        this.postid = postid;
        this.contentType = contentType;
        this.date = date;
        this.description = description;
        this.time = time;
        this.title = title;
        this.user_id = user_id;
    }
}
