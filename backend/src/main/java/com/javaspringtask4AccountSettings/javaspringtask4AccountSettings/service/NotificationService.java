package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.NotificationDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.notification.ChangeNotificationSettingsRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.response.ChangeNotificationResponseDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.ErrorCode;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.GenericExceptionHandler;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Notification;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.jparepository.NotificationRepository;
import org.springframework.http.HttpStatus;
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



    public ChangeNotificationResponseDto changeNotifications(ChangeNotificationSettingsRequest from) {
        User user = userService.getUserByUserId(from.getUserId());

        if(!from.getEnableNotifications()){
            user.setEnabledNotification(from.getEnableNotifications());
            Notification notification = new Notification(false, false, false, user);
            notificationRepository.deleteById(user.getNotification().getNotificationId());
            user.setNotification(notification);
            User updatedUser = userService.save(user);
            return notificationDtoConverter.convert(updatedUser.getNotification(), updatedUser.getEnabledNotification());
        }
        else {
            user.setEnabledNotification(from.getEnableNotifications());
            Notification notification = new Notification(from.getAccountBalanceUpdates(), from.getTransactionAlerts(), from.getSecurityAlerts(), user);
            notificationRepository.deleteById(user.getNotification().getNotificationId());
            user.setNotification(notification);
            User updatedUser = userService.save(user);
            return notificationDtoConverter.convert(updatedUser.getNotification(), updatedUser.getEnabledNotification());
        }
    }

    public ChangeNotificationResponseDto getNotification(String userId) {
        User user = userService.getUserByUserId(userId);
        return notificationDtoConverter.convert(user.getNotification(),user.getEnabledNotification());
    }
}
