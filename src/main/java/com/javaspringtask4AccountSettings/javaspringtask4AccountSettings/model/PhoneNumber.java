package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model;

import jakarta.persistence.*;

@Entity
public class PhoneNumber extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer phoneNumber_Id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    public Integer getPhoneNumber_Id() {
        return phoneNumber_Id;
    }

    public void setPhoneNumber_Id(Integer phoneNumber_Id) {
        this.phoneNumber_Id = phoneNumber_Id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
