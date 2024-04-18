package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.UserDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.password.ChangePasswordRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.register.*;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.ErrorCode;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.GenericExceptionHandler;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.*;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public UserDto getUserByUserId(String userId) {
        return userDtoConverter.convert(userRepository.findById(userId).orElseThrow(() -> GenericExceptionHandler.builder()
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorMessage("User Not Found.")
                .build()));
    }

    @Transactional
    public UserDto saveUser(UserRegisterRequest userRegisterRequest) {
        Boolean twoFactorAuth = userRegisterRequest.getTwoFactorAuth();
        Boolean isEnabledNotification = userRegisterRequest.getIsEnabledNotification();

        User user = new User();
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setTwoFactorAuth(twoFactorAuth);
        user.setEnabledNotification(isEnabledNotification);

        UserProfileRequest userProfileDto = userRegisterRequest.getUserProfile();
        UserProfile userProfile = new UserProfile(
                userProfileDto.getProfileImageUrl(),
                userProfileDto.getMonthlyIncome(),
                userProfileDto.getEducationLevel(),
                userProfileDto.getProfession()
        );
        userProfile.setUser(user);
        userProfileService.saveUserProfile(userProfile);
        user.setUserProfile(userProfile);

        List<EmailRequest> emailDtos = userRegisterRequest.getEmails();
        List<Email> emails = emailDtos.stream()
                .map(emailDto -> new Email(emailDto.getEmail(),user))
                .collect(Collectors.toList());
        emailService.saveAllEmail(emails);
        user.setEmails(emails);

        List<AddressRegisterRequest> addressDtos = userRegisterRequest.getAddresses();
        List<Address> addresses = addressDtos.stream()
                .map(addressDto -> new Address(addressDto.getStreet(), addressDto.getCity(), addressDto.getPostalCode(), addressDto.getCountry()))
                .collect(Collectors.toList());
        user.setAddresses(addresses);

        List<PhoneNumberRequest> phoneNumberDtos = userRegisterRequest.getPhoneNumbers();
        List<PhoneNumber> phoneNumbers = phoneNumberDtos.stream()
                .map(phoneNumberDto -> new PhoneNumber(phoneNumberDto.getPhoneNumber()))
                .collect(Collectors.toList());
        user.setPhoneNumbers(phoneNumbers);

        User newUser = userRepository.save(user);
        System.out.println(newUser.toString());
        return userDtoConverter.convert(newUser);
    }

    public UserDto changePassword(ChangePasswordRequest changePasswordRequest){

        User user = userRepository.findByUserId(changePasswordRequest.getUserId());

        System.out.println(user.toString()+"  enes");
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())){
            throw GenericExceptionHandler.builder()
                    .errorMessage("Current password doesn't match")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .errorCode(ErrorCode.PASSWORD_NOT_MATCH)
                    .build();
        }

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmNewPassword())){
            throw GenericExceptionHandler.builder()
                    .errorMessage("New passwords don't match")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .errorCode(ErrorCode.PASSWORD_NOT_MATCH)
                    .build();
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

        User updatedUser = userRepository.save(user);
        System.out.println(updatedUser.toString());
        return userDtoConverter.convert(updatedUser);
    }


    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(user -> userDtoConverter.convert(user)).collect(Collectors.toList());
    }
}
