package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.password;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
public class ChangePasswordRequest {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String currentPassword;
    @Size(min = 6, message = "New password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "New password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character")
    private String newPassword;
    @Size(min = 6, message = "New password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "New password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character")
    private String confirmNewPassword;
}
