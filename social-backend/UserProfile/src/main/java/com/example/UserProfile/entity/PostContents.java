package com.example.UserProfile.entity;

import java.io.Serializable;

public class PostContents implements Serializable {
    private static final long serialVersionUID = 1L;
    private int contentid;
    private String media_name;
    private String meta_data;
    private String type;
    private int postid;

    public void setContentid(int contentid) {
        this.contentid = contentid;
    }

    public void setMedia_name(String media_name) {
        this.media_name = media_name;
    }

    public void setMeta_data(String meta_data) {
        this.meta_data = meta_data;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getContentid() {
        return contentid;
    }

    public String getMedia_name() {
        return media_name;
    }

    public String getMeta_data() {
        return meta_data;
    }

    public String getType() {
        return type;
    }

    public int getPostid() {
        return postid;
    }

    public PostContents(int contentid, String media_name, String meta_data, String type, int postid) {
        this.contentid = contentid;
        this.media_name = media_name;
        this.meta_data = meta_data;
        this.type = type;
        this.postid = postid;
    }
}
