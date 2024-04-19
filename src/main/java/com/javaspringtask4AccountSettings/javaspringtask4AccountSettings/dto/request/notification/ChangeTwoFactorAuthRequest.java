package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.notification;

import lombok.Data;

@Data
public class ChangeTwoFactorAuthRequest {

    private String userId;
    private Boolean twoFactorAuth;
}
