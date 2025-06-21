package com.example.UserProfile.request;

import java.sql.Timestamp;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class userAccUpdateRequest {
	@JsonProperty("userID")
	private int userID;
    public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	@JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;
    @JsonProperty("bio")
    private String bio;
    @JsonProperty("birth_date")
    private Timestamp birth_date;
    @JsonProperty("mobile")
    private int mobile;
    @JsonProperty("address")
    private String address;
    @JsonProperty("job")
    private String job;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("choice")
    private int choice;
    
    // No-arg constructor (required for Jackson/JPA)
    public userAccUpdateRequest() {}

    public userAccUpdateRequest(int userID, String first_name, String last_name, String bio, String profile_pic_url,
			Timestamp birth_date, int mobile, String address, String job, String userName, String password,
			 int choice) {
		super();
		this.userID = userID;
		this.first_name = first_name;
		this.last_name = last_name;
		this.bio = bio;
		this.birth_date = birth_date;
		this.mobile = mobile;
		this.address = address;
		this.job = job;
		this.userName = userName;
		this.password = password;
		this.choice = choice;
	}

	public String getFirst_name() { return first_name; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }

    public String getLast_name() { return last_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public Timestamp getBirth_date() { return birth_date; }  // Fixed getter name
    public void setBirth_date(Timestamp birth_date) { this.birth_date = birth_date; }

    public int getMobile() { return mobile; }
    public void setMobile(int mobile) { this.mobile = mobile; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
    
}
