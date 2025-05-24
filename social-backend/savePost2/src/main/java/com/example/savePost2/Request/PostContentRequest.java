package com.example.savePost2.Request;

import com.example.savePost2.Entity.Posts;
import jakarta.persistence.*;

public class PostContentRequest {
    private int contentID;
    private String type;
    private String mediaName;
    private String metaData;
    private Posts post;

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

    public PostContentRequest() {
    }

    public PostContentRequest(String type, String mediaName, String metaData, Posts post) {
        this.type = type;
        this.mediaName = mediaName;
        this.metaData = metaData;
        this.post = post;
    }
}
