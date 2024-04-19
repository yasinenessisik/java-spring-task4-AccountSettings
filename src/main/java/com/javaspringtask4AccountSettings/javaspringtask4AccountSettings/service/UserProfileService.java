package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserProfileDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.UserProfileDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.profile.ChangeProfileRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.UserProfile;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private final UserProfileDtoConverter userProfileDtoConverter;
    private final UserProfileRepository userProfileRepository;
    private final UserService userService;

    public UserProfileService(UserProfileDtoConverter userProfileDtoConverter, UserProfileRepository userProfileRepository, UserService userService) {
        this.userProfileDtoConverter = userProfileDtoConverter;
        this.userProfileRepository = userProfileRepository;
        this.userService = userService;
    }

    public UserProfileDto saveUserProfile(UserProfile userProfile) {
        return userProfileDtoConverter.convert(userProfile);
    }

    public UserProfileDto changeProfile(ChangeProfileRequest changeProfileRequest) {

        User user = userService.getUserByUserId(changeProfileRequest.getUserId());

        UserProfile newUserProfile = user.getUserProfile();

        newUserProfile.setProfileImageUrl(changeProfileRequest.getProfileImageUrl());
        newUserProfile.setProfession(changeProfileRequest.getProfession());
        newUserProfile.setEducationLevel(changeProfileRequest.getEducationLevel());
        newUserProfile.setMonthlyIncome(changeProfileRequest.getMonthlyIncome());


        UserDto updatedUser = userService.save(user);

        return updatedUser.getUserProfile();
    }

    public UserProfileDto getUserProfile(String userId) {
        User user = userService.getUserByUserId(userId);
        return userProfileDtoConverter.convert(user.getUserProfile());
    }
}
