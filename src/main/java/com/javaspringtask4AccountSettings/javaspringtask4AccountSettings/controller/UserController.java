package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.controller;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.AddressDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.DeleteAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.GetAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.SaveAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.UpdateAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.notification.ChangeNotificationSettingsRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.notification.ChangeTwoFactorAuthRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.password.ChangePasswordRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.profile.ChangeProfileRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.register.UserRegisterRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.AddressService;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.NotificationService;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.UserProfileService;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/controller/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("saveUser")
    public UserDto saveUser(@RequestBody  UserRegisterRequest userRegisterRequest){
        return userService.saveUser(userRegisterRequest);
    }

    @PatchMapping("changePassword")
    public UserDto changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return userService.changePassword(changePasswordRequest);
    }

    @PatchMapping("changeTwoFactorAuth")
    public UserDto changeNotifications(@RequestBody ChangeTwoFactorAuthRequest changeTwoFactorAuthRequest){
        return userService.changeTwoFactorAuth(changeTwoFactorAuthRequest);
    }

    @GetMapping("getAll")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @GetMapping("getAll2")
    public List<User> getAllUser2(){
        return userService.getAllUser2();
    }
}
