package com.example.login.Entity;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "user_security_logs")
public class user_security_logs {
    public enum ActionLevel {
        LOW, MEDIUM, HIGH
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int log_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private users user_id;
    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private ActionLevel action;
    @Column(name="ip_address")
    private String ip_address;
    @Column(name="created_at")
    private Time created_at;

    public int getLog_id() {
        return log_id;
    }

    public users getUser_id() {
        return user_id;
    }

    public ActionLevel getAction() {
        return action;
    }

    public String getIp_address() {
        return ip_address;
    }

    public Time getCreated_at() {
        return created_at;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public void setUser_id(users user_id) {
        this.user_id = user_id;
    }

    public void setAction(ActionLevel action) {
        this.action = action;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public void setCreated_at(Time created_at) {
        this.created_at = created_at;
    }
}
