package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.controller;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.AddressDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserProfileDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.GetAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.profile.ChangeProfileRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/controller/userProfile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }
    @PatchMapping("changeProfile")
    public ResponseEntity<UserProfileDto> changeProfile(@RequestBody ChangeProfileRequest changeProfileRequest){
        return ResponseEntity.ok(userProfileService.changeProfile(changeProfileRequest));
    }
    @GetMapping("/{userId}/getUserProfile")
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable String userId) {
        return ResponseEntity.ok(userProfileService.getUserProfile(userId));
    }
}
