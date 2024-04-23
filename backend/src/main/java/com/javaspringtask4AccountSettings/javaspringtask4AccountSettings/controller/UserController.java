package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.controller;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.notification.ChangeTwoFactorAuthRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.password.ChangePasswordRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.register.UserRegisterRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.response.TwoFactoAuthResponse;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.response.UserResponse;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.UserService;
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
    public UserDto saveUser(@RequestBody  UserRegisterRequest userRegisterRequest){
        return userService.saveUser(userRegisterRequest);
    }

    @PatchMapping("changePassword")
    public UserDto changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return userService.changePassword(changePasswordRequest);
    }

    @PatchMapping("changeTwoFactorAuth")
    public UserDto changeFactorAuth(@RequestBody ChangeTwoFactorAuthRequest changeTwoFactorAuthRequest){
        return userService.changeTwoFactorAuth(changeTwoFactorAuthRequest);
    }
    @GetMapping("getTwoFactorAuth/{userId}")
    public ResponseEntity<TwoFactoAuthResponse> getFactorAuth(@PathVariable String userId){
        return ResponseEntity.ok(userService.getTwoFactorAuth(userId));
    }

    @GetMapping("getAll")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("getById/{getByIdWithCache}")
    public  User getUserById(@PathVariable String getByIdWithCache){
        return userService.getUserByUserId(getByIdWithCache);
    }
    @GetMapping("getAll2")
    public List<User> getAllUserWithCache(){
        return userService.getAllUserWithCache();
    }

    @GetMapping("getByIdWithCache/{getByIdWithCache}")
    public User getAllUserWithCache(@PathVariable String getByIdWithCache){
        return userService.getByIdWithCache(getByIdWithCache);
    }

    @GetMapping("reset")
    public void resetCache(){
        userService.resetCache();
    }
    @GetMapping("getByIdMain/{userId}")
    public UserResponse getByIdMain(@PathVariable String userId){
        return userService.getUserByUserIdMainScreen(userId);
    }
}
