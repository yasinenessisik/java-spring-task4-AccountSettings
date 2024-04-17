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

    public UserProfile(String profileImageUrl, BigDecimal monthlyIncome, EducationLevel educationLevel, String profession) {
        this.profileImageUrl = profileImageUrl;
        this.monthlyIncome = monthlyIncome;
        this.educationLevel = educationLevel;
        this.profession = profession;
    }

    public Integer getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Integer userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
