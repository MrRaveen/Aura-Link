package com.example.createAccountService.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="UserInterests")
public class Interests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="interestId")
    private int interestId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private users user_id;
    @Column(name="name")
    private String name;
    @Column(name="category")
    private String category;

    public Interests(users user_id, String name, String category) {
        this.user_id = user_id;
        this.name = name;
        this.category = category;
    }

    public int getInterestId() {
        return interestId;
    }

    public users getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setInterestId(int interestId) {
        this.interestId = interestId;
    }

    public void setUser_id(users user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
