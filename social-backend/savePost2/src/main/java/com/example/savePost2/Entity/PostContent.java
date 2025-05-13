package com.example.savePost2.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PostContent")
public class PostContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contentID")
    private int contentID;
    @Column(name = "type")
    private String type;
    @Column(name = "mediaName")
    private String mediaName;
    @Column(name = "metaData")
    private String metaData;
    @ManyToOne
    @JoinColumn(name = "postid",nullable = false)
    private Posts post;

    public PostContent(String type, String mediaName, String metaData, Posts post) {
        this.type = type;
        this.mediaName = mediaName;
        this.metaData = metaData;
        this.post = post;
    }

    public PostContent() {
    }

    public int getContentID() {
        return contentID;
    }

    public String getType() {
        return type;
    }

    public String getMediaName() {
        return mediaName;
    }

    public String getMetaData() {
        return metaData;
    }

    public Posts getPost() {
        return post;
    }

    public void setContentID(int contentID) {
        this.contentID = contentID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public void setPost(Posts post) {
        this.post = post;
    }
}
