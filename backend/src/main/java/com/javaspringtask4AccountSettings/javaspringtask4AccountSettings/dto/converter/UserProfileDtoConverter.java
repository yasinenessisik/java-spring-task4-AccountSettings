package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.PhoneNumberDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserProfileDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileDtoConverter {
    public UserProfileDto convert(UserProfile from){
        return UserProfileDto.builder()
                .userProfileId(from.getUserProfileId())
                .educationLevel(from.getEducationLevel())
                .monthlyIncome(from.getMonthlyIncome())
                .profession(from.getProfession())
                .build();
    }
}
