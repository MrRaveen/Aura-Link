package com.example.createAccountService.Requests;

import java.util.List;

public class InterestRequestList {
    List<Interest> allRequests;
    public InterestRequestList() {
    }
    public List<Interest> getAllRequests() {
        return allRequests;
    }
    public void setAllRequests(List<Interest> allRequests) {
        this.allRequests = allRequests;
    }
}
