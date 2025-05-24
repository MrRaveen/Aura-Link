package com.example.createAccountService.Responses;

public class EligibilityResponse {
    boolean userName;
    boolean email;
    public EligibilityResponse(boolean userName, boolean email) {
        this.userName = userName;
        this.email = email;
    }

    public void setUserName(boolean userName) {
        this.userName = userName;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }

    public boolean isUserName() {
        return userName;
    }

    public boolean isEmail() {
        return email;
    }
}
