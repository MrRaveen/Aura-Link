package com.example.UserProfile.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document("CheckEmailVerification")
public class CheckEmailVerificationMongo {
	@Id
	private String id;
	private String verificationID;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVerificationID() {
		return verificationID;
	}
	public void setVerificationID(String verificationID) {
		this.verificationID = verificationID;
	}
	public CheckEmailVerificationMongo(String id, String verificationID) {
		super();
		this.id = id;
		this.verificationID = verificationID;
	}
}
