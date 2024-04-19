package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.notification;

import lombok.Data;

@Data
public class ChangeNotificationSettingsRequest {
    private String userId;
    private Boolean enableNotifications;
    private Boolean accountBalanceUpdates;
    private Boolean transactionAlerts;
    private Boolean securityAlerts;
}
