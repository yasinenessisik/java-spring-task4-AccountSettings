package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.*;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.UserDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.register.*;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.ErrorCode;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.GenericExceptionHandler;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.*;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final UserProfileService userProfileService;
    private final EmailService emailService;
    private final PhoneNumberService phoneNumberService;
    private final AddressService addressService;

    public UserDto getUserByUserId(String userId){
        return userDtoConverter.convert(userRepository.findById(userId).orElseThrow(()-> GenericExceptionHandler.builder()
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorMessage("User Not Found.")
                .build()));
    }

    @Transactional
    public UserDto saveUser(UserRegisterRequest userRegisterRequest){
        String password = userRegisterRequest.getPassword();
        Boolean twoFactorAuth = userRegisterRequest.getTwoFactorAuth();
        Boolean isEnabledNotification = userRegisterRequest.getIsEnabledNotification();

        User user = new User();
        user.setPassword(password);
        user.setTwoFactorAuth(twoFactorAuth);
        user.setEnabledNotification(isEnabledNotification);

        // Save UserProfile first
        UserProfileRequest userProfileDto = userRegisterRequest.getUserProfile();
        UserProfile userProfile = new UserProfile(
                userProfileDto.getProfileImageUrl(),
                userProfileDto.getMonthlyIncome(),
                userProfileDto.getEducationLevel(),
                userProfileDto.getProfession()
        );
        userProfileService.saveUserProfile(userProfile);

        // Now set the saved UserProfile in User
        user.setUserProfile(userProfile);

        // Save other related entities
        List<EmailRequest> emailDtos = userRegisterRequest.getEmails();
        List<Email> emails = emailDtos.stream()
                .map(emailDto -> new Email(emailDto.getEmail()))
                .collect(Collectors.toList());
        emailService.saveAllEmail(emails);
        user.setEmails(emails);

        List<AddressRegisterRequest> addressDtos = userRegisterRequest.getAddresses();
        List<Address> addresses = addressDtos.stream()
                .map(addressDto -> new Address(addressDto.getStreet(), addressDto.getCity(), addressDto.getPostalCode(),addressDto.getCountry()))
                .collect(Collectors.toList());
        addressService.saveAllAddress(addresses);
        user.setAddresses(addresses);

        List<PhoneNumberRequest> phoneNumberDtos = userRegisterRequest.getPhoneNumbers();
        List<PhoneNumber> phoneNumbers = phoneNumberDtos.stream()
                .map(phoneNumberDto -> new PhoneNumber(phoneNumberDto.getPhoneNumber()))
                .collect(Collectors.toList());
        phoneNumberService.saveAllPhoneNumber(phoneNumbers);
        user.setPhoneNumbers(phoneNumbers);

        userRepository.save(user);

        return userDtoConverter.convert(user);
    }
}
