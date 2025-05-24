package com.example.UserProfile.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalTime;

@Entity
@Table(name = "posts")
public class postEntity {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public postEntity() {

    }

    public enum content_type{
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
    @Column(name = "content_type")
    private content_type content_type;
    @Column(name = "date")
    private Date date;
    @Column(name = "description")
    private String description;
    @Column(columnDefinition = "time(6)")
    private LocalTime time;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user_id;

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public void setContent_type(content_type content_type) {
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

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }

    public int getPostid() {
        return postid;
    }

    public content_type getContent_type() {
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

    public Users getUser_id() {
        return user_id;
    }

    public postEntity(content_type content_type, Date date, String description, LocalTime time, String title, Users user_id) {
        this.content_type = content_type;
        this.date = date;
        this.description = description;
        this.time = time;
        this.title = title;
        this.user_id = user_id;
    }
}
