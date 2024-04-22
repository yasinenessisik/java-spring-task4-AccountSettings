package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Integer notificationId;

    private Boolean accountBalanceUpdate;

    private Boolean transcationUpdate;

    private Boolean securityAlert;
}
