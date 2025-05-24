package com.example.login.Entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "sessions")
public class sessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private int session_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private users user_id;
    @Column(name = "ip_address")
    private String ip_address;
    @Column(name = "user_agent")
    private String user_agent;
    @Column(name = "Date")
    private Date Date;
    @Column(name = "time")
    private Time time;
    @Column(name = "expires_at")
    private Time expires_at;

    public String getUser_agent() {
        return user_agent;
    }

    public Time getExpires_at() {
        return expires_at;
    }

    public int getSession_id() {
        return session_id;
    }

    public users getUser_id() {
        return user_id;
    }

    public String getIp_address() {
        return ip_address;
    }

    public Date getDate() {
        return Date;
    }

    public Time getTime() {
        return time;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public void setUser_id(users user_id) {
        this.user_id = user_id;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public void setExpires_at(Time expires_at) {
        this.expires_at = expires_at;
    }
}
