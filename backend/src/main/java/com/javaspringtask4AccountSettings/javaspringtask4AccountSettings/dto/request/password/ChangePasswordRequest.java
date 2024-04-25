package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.password;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

@Data
public class ChangePasswordRequest {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String currentPassword;
    @NotEmpty
    private String newPassword;
    @NotEmpty
    private String confirmNewPassword;
}
