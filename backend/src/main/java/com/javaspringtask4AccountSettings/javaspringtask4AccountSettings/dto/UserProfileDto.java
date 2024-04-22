package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.EducationLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private Integer userProfileId;

    private String profileImageUrl;

    private BigDecimal monthlyIncome;

    private EducationLevel educationLevel;

    private String profession;
}
