package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
public class UserProfile extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userProfileId;

    private String profileImageUrl;

    private BigDecimal monthlyIncome;

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    private String profession;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;


}
