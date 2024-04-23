package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.profile;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.EducationLevel;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ChangeProfileRequest {
    private String userId;

    private BigDecimal monthlyIncome;

    private EducationLevel educationLevel;

    private String profession;
}
