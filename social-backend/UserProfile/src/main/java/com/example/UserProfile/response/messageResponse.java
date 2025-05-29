package com.example.UserProfile.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class messageResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private int ststus;

    public messageResponse() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStstus(int ststus) {
        this.ststus = ststus;
    }

    public String getMessage() {
        return message;
    }

    public int getStstus() {
        return ststus;
    }

    public messageResponse(String message, int ststus) {
        this.message = message;
        this.ststus = ststus;
    }
}
