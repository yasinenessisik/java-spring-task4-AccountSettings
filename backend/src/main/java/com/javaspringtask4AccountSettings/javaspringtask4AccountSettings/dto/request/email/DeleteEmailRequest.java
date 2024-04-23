package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteEmailRequest {
    @NotEmpty
    private String userId;
    @NotNull
    private Integer emailId;
}
