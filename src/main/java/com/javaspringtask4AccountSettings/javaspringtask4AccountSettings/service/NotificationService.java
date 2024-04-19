package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.NotificationDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.NotificationDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.notification.ChangeNotificationSettingsRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Notification;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserService userService;
    private final NotificationDtoConverter notificationDtoConverter;

    public NotificationService(NotificationRepository notificationRepository, UserService userService, NotificationDtoConverter notificationDtoConverter) {
        this.notificationRepository = notificationRepository;
        this.userService = userService;
        this.notificationDtoConverter = notificationDtoConverter;
    }

    public void saveNotification(Notification notification){
        notificationRepository.save(notification);
    }
    public UserDto changeNotifications(ChangeNotificationSettingsRequest changeNotificationSettingsRequest) {
        User user = userService.getUserByUserId(changeNotificationSettingsRequest.getUserId());

        user.setEnabledNotification(changeNotificationSettingsRequest.getEnableNotifications());
        user.getNotification().setAccountBalanceUpdate(changeNotificationSettingsRequest.getAccountBalanceUpdates());
        user.getNotification().setSecurityAlert(changeNotificationSettingsRequest.getSecurityAlerts());
        user.getNotification().setTranscationUpdate(changeNotificationSettingsRequest.getTransactionAlerts());

        UserDto updatedUser = userService.save(user);
        return updatedUser;
    }

    public NotificationDto getNotification(String userId) {
        User user = userService.getUserByUserId(userId);
        return notificationDtoConverter.convert(user.getNotification());
    }
}
