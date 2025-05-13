package com.example.createAccountService.Requests;

import com.example.createAccountService.Entity.users;

public class Interest {
    private int user_id;
    private String name;
    private String category;

    public Interest(int user_id, String name, String category) {
        this.user_id = user_id;
        this.name = name;
        this.category = category;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
