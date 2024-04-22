package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.phonenumber;

import lombok.Data;

@Data
public class SavePhoneNumberRequest {
    private String userId;
    private String phoneNumber;
}
