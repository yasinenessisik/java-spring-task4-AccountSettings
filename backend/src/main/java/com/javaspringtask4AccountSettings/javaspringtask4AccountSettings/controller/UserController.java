package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.controller;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.notification.ChangeTwoFactorAuthRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.password.ChangePasswordRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.register.UserRegisterRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.response.TwoFactoAuthResponse;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.response.UserResponse;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public UserDto saveUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.saveUser(userRegisterRequest);
    }
    //git

    @PatchMapping("changePassword")
    public UserDto changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return userService.changePassword(changePasswordRequest);
    }

    @PatchMapping("changeTwoFactorAuth")
    public UserDto changeFactorAuth(@Valid @RequestBody ChangeTwoFactorAuthRequest changeTwoFactorAuthRequest) {
        return userService.changeTwoFactorAuth(changeTwoFactorAuthRequest);
    }

    @GetMapping("getTwoFactorAuth/{userId}")
    public ResponseEntity<TwoFactoAuthResponse> getFactorAuth(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getTwoFactorAuth(userId));
    }

    @GetMapping("getAll")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("getByIdMain/{userId}")
    public UserResponse getUserById(@PathVariable String userId) {
        return userService.getUserByUserIdMainScreen(userId);
    }
    @GetMapping("getByIdM/{userId}")
    public UserDto getUserBy(@PathVariable String userId) {
        return userService.getUserByUserIdPublic(userId);
    }
    @GetMapping("resetCache/{userId}")
    public void resetCache(@PathVariable String userId) {
        userService.resetCache(userId);
    }
}
