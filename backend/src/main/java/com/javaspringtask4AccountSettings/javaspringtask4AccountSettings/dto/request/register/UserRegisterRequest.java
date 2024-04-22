package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.register;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.AddressDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.EmailDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.PhoneNumberDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserProfileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    private String firstName;

    private String lastName;

    private String password;

    private List<EmailRequest> emails;

    private List<AddressRegisterRequest> addresses;

    private UserProfileRequest userProfile;

    private List<PhoneNumberRequest> phoneNumbers;

    private NotificationRegisterRequest notificationRegisterRequest;

    private Boolean twoFactorAuth;

    private Boolean isEnabledNotification;
}
