package com.example.UserProfile.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "post_content")
public class PostContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contentid")
    private int contentid;
    @Column(name = "media_name")
    private String media_name;
    @Column(name = "meta_data")
    private String meta_data;
    @Column(name = "type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "postid", nullable = false)
    private postEntity postid;


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

    public void setPostid(postEntity postid) {
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

    public postEntity getPostid() {
        return postid;
    }

    public PostContentEntity() {
    }

    public PostContentEntity(String media_name, String meta_data, String type, postEntity postid) {
        this.media_name = media_name;
        this.meta_data = meta_data;
        this.type = type;
        this.postid = postid;
    }
}
