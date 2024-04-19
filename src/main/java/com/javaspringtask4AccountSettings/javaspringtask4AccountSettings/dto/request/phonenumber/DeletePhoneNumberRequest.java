package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.phonenumber;

import lombok.Data;

@Data
public class DeletePhoneNumberRequest {
    private String userId;
    private Integer phoneNumberId;
}
