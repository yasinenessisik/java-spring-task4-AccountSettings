package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRegisterRequest {
    private Boolean accountBalanceUpdate;

    private Boolean transcationUpdate;

    private Boolean securityAlert;
}
