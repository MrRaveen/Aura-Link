package com.example.UserProfile.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Document("userNotifications")
public class notificationsMongo {
    public enum NotificationType{
        REACTION,
        POST,
        REPLY
    }
    @Id
    private int ID;
    private int recipientUserId;
    private int actorUserId;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    private String header;
    private String body; //title
    private boolean isRead;
    private LocalDate createdDate;
    private Timestamp createdTime;

    public notificationsMongo() {
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setRecipientUserId(int recipientUserId) {
        this.recipientUserId = recipientUserId;
    }

    public void setActorUserId(int actorUserId) {
        this.actorUserId = actorUserId;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public int getID() {
        return ID;
    }

    public int getRecipientUserId() {
        return recipientUserId;
    }

    public int getActorUserId() {
        return actorUserId;
    }

    public NotificationType getType() {
        return type;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public boolean isRead() {
        return isRead;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public notificationsMongo(int recipientUserId, int actorUserId, NotificationType type, String header, String body, boolean isRead, LocalDate createdDate, Timestamp createdTime) {
        this.recipientUserId = recipientUserId;
        this.actorUserId = actorUserId;
        this.type = type;
        this.header = header;
        this.body = body;
        this.isRead = isRead;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
    }
}
