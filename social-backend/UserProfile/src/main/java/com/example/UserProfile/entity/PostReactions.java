package com.example.UserProfile.entity;

import java.io.Serializable;

public class PostReactions implements Serializable {
    private static final long serialVersionUID = 1L;
    private int laugh;
    private int like;
    private int love;
    private int sad;

    public void setLaugh(int laugh) {
        this.laugh = laugh;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public void setSad(int sad) {
        this.sad = sad;
    }

    public int getLaugh() {
        return laugh;
    }

    public int getLike() {
        return like;
    }

    public int getLove() {
        return love;
    }

    public int getSad() {
        return sad;
    }

    public PostReactions(int laugh, int like, int love, int sad) {
        this.laugh = laugh;
        this.like = like;
        this.love = love;
        this.sad = sad;
    }
}
