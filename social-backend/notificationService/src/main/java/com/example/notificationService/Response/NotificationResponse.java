package com.example.notificationService.Response;

import java.time.LocalDate;

public class NotificationResponse {
	private String id;
	private String type;
	private String header;
	private String body;
	private LocalDate createdDate;
	private int recipientUserId;
    private int actorUserId;
    private boolean isRead;
    private String URL;
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public int getRecipientUserId() {
		return recipientUserId;
	}
	public void setRecipientUserId(int recipientUserId) {
		this.recipientUserId = recipientUserId;
	}
	public int getActorUserId() {
		return actorUserId;
	}
	public void setActorUserId(int actorUserId) {
		this.actorUserId = actorUserId;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public NotificationResponse(String id, String type, String header, String body, LocalDate createdDate,
			int recipientUserId, int actorUserId, boolean isRead, String uRL) {
		super();
		this.id = id;
		this.type = type;
		this.header = header;
		this.body = body;
		this.createdDate = createdDate;
		this.recipientUserId = recipientUserId;
		this.actorUserId = actorUserId;
		this.isRead = isRead;
		URL = uRL;
	}
	public NotificationResponse() {
		super();
	}
	
    
}
