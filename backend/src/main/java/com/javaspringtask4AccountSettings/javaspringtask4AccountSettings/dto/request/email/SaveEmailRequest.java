package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveEmailRequest {
    @NotEmpty(message = "User not be empty.")
    private String userId;
    @Email(message = "Please enter valid email")
    private String email;
}
