package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.controller;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.NotificationDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserProfileDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.notification.ChangeNotificationSettingsRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.response.ChangeNotificationResponseDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/controller/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @PatchMapping("changeNotifications")
    public ResponseEntity<ChangeNotificationResponseDto> changeNotifications(@RequestBody ChangeNotificationSettingsRequest changeNotificationSettingsRequest){
        return ResponseEntity.ok(notificationService.changeNotifications(changeNotificationSettingsRequest));
    }
    @GetMapping("/{userId}/getNotification")
    public ResponseEntity<ChangeNotificationResponseDto> getNotification(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getNotification(userId));
    }
}
