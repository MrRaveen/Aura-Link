package com.example.UserProfile.entity;

import java.util.List;

public class PostContentHolder {
    private List<PostContents> post_contentsArr;

    public void setPost_contentsArr(List<PostContents> post_contentsArr) {
        this.post_contentsArr = post_contentsArr;
    }

    public List<PostContents> getPost_contentsArr() {
        return post_contentsArr;
    }

    public PostContentHolder(List<PostContents> post_contentsArr) {
        this.post_contentsArr = post_contentsArr;
    }
}
