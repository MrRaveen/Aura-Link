package com.example.UserProfile.entity;

public class PostComments {
    private int commentid;
    private String comment;
    private int postid;
    private int user_id;
    private String first_name;
    private String last_name;

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getCommentid() {
        return commentid;
    }

    public String getComment() {
        return comment;
    }

    public int getPostid() {
        return postid;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public PostComments(int commentid, String comment, int postid, int user_id, String first_name, String last_name) {
        this.commentid = commentid;
        this.comment = comment;
        this.postid = postid;
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
