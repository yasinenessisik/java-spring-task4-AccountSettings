package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.NotificationDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationDtoConverter {
    public NotificationDto convert(Notification from){
        return NotificationDto.builder()
                .notificationId(from.getNotificationId())
                .accountBalanceUpdate(from.getAccountBalanceUpdate())
                .securityAlert(from.getSecurityAlert())
                .transcationUpdate(from.getTranscationUpdate())
                .build();
    }
}
