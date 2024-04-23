package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeNotificationResponseDto {
    private Boolean enableNotifications;
    private Boolean accountBalanceUpdates;
    private Boolean transactionAlerts;
    private Boolean securityAlerts;
}
