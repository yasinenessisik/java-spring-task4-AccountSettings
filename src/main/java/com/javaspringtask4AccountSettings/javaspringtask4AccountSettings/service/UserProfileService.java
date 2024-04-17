package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserProfileDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.UserProfileDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.UserProfile;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private final UserProfileDtoConverter userProfileDtoConverter;
    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileDtoConverter userProfileDtoConverter, UserProfileRepository userProfileRepository) {
        this.userProfileDtoConverter = userProfileDtoConverter;
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileDto saveUserProfile(UserProfile userProfile){
        return userProfileDtoConverter.convert(userProfile);
    }
}
