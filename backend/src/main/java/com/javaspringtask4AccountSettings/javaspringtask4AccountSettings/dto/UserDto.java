package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("user")
public class UserDto implements Serializable{
    @Id
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

    private String createdAt;

    private String updatedAt;

    @TimeToLive
    private Long timeToLive;
}
