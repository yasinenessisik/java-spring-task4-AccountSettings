package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Address;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Email;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.PhoneNumber;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto{
    private String userId;

    private String firstName;

    private String lastName;
    private String profilePhotoPath;
    private String password;

    private List<EmailDto> emails;

    private List<AddressDto> addresses;

    private UserProfileDto userProfile;

    private List<PhoneNumberDto> phoneNumbers;

    private NotificationDto notificationDto;

    private Boolean twoFactorAuth;

    private Boolean isEnabledNotification;
}
