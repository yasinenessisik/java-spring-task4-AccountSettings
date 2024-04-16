package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String userId;

    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Email> emails;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Address> addresses;

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<PhoneNumber> phoneNumbers;
    private Boolean twoFactorAuth;

    private Boolean isEnabledNotification;


}

