package com.example.savePost2.Entity;

import com.example.savePost2.Entity.Posts;
import com.example.savePost2.Entity.users;
import jakarta.persistence.*;

@Entity
@Table(name = "PostComments")
public class PostComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentID")
    private int commentID;
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private users user_id;
    @ManyToOne
    @JoinColumn(name = "postid",nullable = false)
    private Posts post;

    public PostComments(String comment, users user_id, Posts post) {
        this.comment = comment;
        this.user_id = user_id;
        this.post = post;
    }

    public PostComments() {
    }

    public int getCommentID() {
        return commentID;
    }

    public String getComment() {
        return comment;
    }

    public users getUser_id() {
        return user_id;
    }

    public Posts getPost() {
        return post;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser_id(users user_id) {
        this.user_id = user_id;
    }

    public void setPost(Posts post) {
        this.post = post;
    }
}
