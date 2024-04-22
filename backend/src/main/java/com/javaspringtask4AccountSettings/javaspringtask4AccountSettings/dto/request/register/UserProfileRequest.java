package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.register;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRequest {

    private String profileImageUrl;

    private BigDecimal monthlyIncome;

    private EducationLevel educationLevel;

    private String profession;
}
