package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.password;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String userId;
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
}
