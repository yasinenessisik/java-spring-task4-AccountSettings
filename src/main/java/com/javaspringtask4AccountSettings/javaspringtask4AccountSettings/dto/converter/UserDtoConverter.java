package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDtoConverter {
    private final EmailDtoConverter emailDtoConverter;
    private final UserProfileDtoConverter userProfileDtoConverter;
    private final PhoneNumberDtoConverter phoneNumberDtoConverter;
    private final NotificationDtoConverter notificationDtoConverter;
    private final AddressDtoConverter addressDtoConverter;

    public UserDto convert(User from){
        return UserDto.builder()
                .userId(from.getUserId())
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .password(from.getPassword())
                .profilePhotoPath(from.getProfilePhotoPath())
                .emails(from.getEmails().stream().map(email -> emailDtoConverter.convert(email)).collect(Collectors.toList()))
                .addresses(from.getAddresses().stream().map(address -> addressDtoConverter.convert(address)).collect(Collectors.toList()))
                .userProfile(userProfileDtoConverter.convert(from.getUserProfile()))
                .phoneNumbers(from.getPhoneNumbers().stream().map(phoneNumber -> phoneNumberDtoConverter.convert(phoneNumber)).collect(Collectors.toList()))
                .notificationDto(notificationDtoConverter.convert(from.getNotification()))
                .twoFactorAuth(from.getTwoFactorAuth())
                .isEnabledNotification(from.getEnabledNotification())
                .build();
    }

}
