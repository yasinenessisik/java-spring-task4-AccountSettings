package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
public class Email extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer emailId;
    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Email(String email,User user) {
        this.email = email;
        this.user = user;
    }

    public Email() {

    }

    public Integer getEmailId() {
        return emailId;
    }

    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}