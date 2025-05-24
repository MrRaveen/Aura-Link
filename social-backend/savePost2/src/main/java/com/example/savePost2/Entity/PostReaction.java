package com.example.savePost2.Entity;

import com.example.savePost2.Entity.Posts;
import com.example.savePost2.Entity.users;
import jakarta.persistence.*;

@Entity
@Table(name = "PostReaction")
public class PostReaction {
    public enum ReactTypes{
        LIKE,
        LOVE,
        LAUGH,
        SAD
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reactionID")
    private int reactionID;
    @Enumerated(EnumType.STRING)
    @Column(name = "reactType")
    private ReactTypes reactType;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private users user_id;
    @ManyToOne
    @JoinColumn(name = "postid",nullable = false)
    private Posts post;

    public PostReaction() {
    }

    public void setReactionID(int reactionID) {
        this.reactionID = reactionID;
    }

    public void setReactType(ReactTypes reactType) {
        this.reactType = reactType;
    }

    public void setUser_id(users user_id) {
        this.user_id = user_id;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    public int getReactionID() {
        return reactionID;
    }

    public ReactTypes getReactType() {
        return reactType;
    }

    public users getUser_id() {
        return user_id;
    }

    public Posts getPost() {
        return post;
    }

    public PostReaction(ReactTypes reactType, users user_id, Posts post) {
        this.reactType = reactType;
        this.user_id = user_id;
        this.post = post;
    }
}
