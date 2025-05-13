package com.example.UserProfile.request;

public class updatePostRequest {
    private String title;
    private String description;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public updatePostRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
