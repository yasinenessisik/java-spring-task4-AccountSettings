package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.phonenumber;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SavePhoneNumberRequest {
    @NotBlank
    private String userId;
    @Pattern(regexp ="[0-9\\s]{11}")
    private String phoneNumber;
}
