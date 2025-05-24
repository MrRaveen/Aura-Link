package com.example.UserProfile.response;

import com.example.UserProfile.entity.PostContentHolder;
import com.example.UserProfile.entity.PostContents;
import com.example.UserProfile.entity.PostReactions;
import com.example.UserProfile.entity.posts;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class PostAllContainers implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("out1")
    private List<posts> post;
    @JsonProperty("out3")
    private List<List<PostContents>> postContentHolder;
    @JsonProperty("reaction")
    private List<PostReactions> postReactions;

    public void setPost(List<posts> post) {
        this.post = post;
    }

    public void setPostContentHolder(List<List<PostContents>> postContentHolder) {
        this.postContentHolder = postContentHolder;
    }

    public void setPostReactions(List<PostReactions> postReactions) {
        this.postReactions = postReactions;
    }

    public List<posts> getPost() {
        return post;
    }

    public List<List<PostContents>> getPostContentHolder() {
        return postContentHolder;
    }

    public List<PostReactions> getPostReactions() {
        return postReactions;
    }

    public PostAllContainers(List<posts> post, List<List<PostContents>> postContentHolder, List<PostReactions> postReactions) {
        this.post = post;
        this.postContentHolder = postContentHolder;
        this.postReactions = postReactions;
    }
    public PostAllContainers() {

    }
}
