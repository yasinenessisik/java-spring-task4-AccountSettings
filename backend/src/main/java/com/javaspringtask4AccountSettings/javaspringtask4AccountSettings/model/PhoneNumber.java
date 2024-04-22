package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
public class PhoneNumber extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer phoneNumber_Id;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public PhoneNumber(String phoneNumber,User user)
    {
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

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
