package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.UserDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.notification.ChangeTwoFactorAuthRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.password.ChangePasswordRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.register.UserRegisterRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.ErrorCode;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.GenericExceptionHandler;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.*;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.jparepository.UserRepository;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.util.CacheNames;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final PasswordEncoder passwordEncoder;

    public User getUserByUserId(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> GenericExceptionHandler.builder()
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorMessage("User Not Found.")
                .build());
    }

    @Caching(
            evict = {
                    @CacheEvict(value = CacheNames.USER, allEntries = true)
            }
    )
    @Transactional
    public UserDto saveUser(UserRegisterRequest userRegisterRequest) {

        Boolean twoFactorAuth = userRegisterRequest.getTwoFactorAuth();
        Boolean isEnabledNotification = userRegisterRequest.getIsEnabledNotification();
        String firstName = userRegisterRequest.getFirstName();
        String lastName = userRegisterRequest.getLastName();
        String uniquePath = UUID.randomUUID().toString();

        User user = new User();

        user.setProfilePhotoPath(uniquePath+".jpg");
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setTwoFactorAuth(twoFactorAuth);
        user.setEnabledNotification(isEnabledNotification);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        UserProfile userProfile = new UserProfile(
                userRegisterRequest.getUserProfile().getProfileImageUrl(),
                userRegisterRequest.getUserProfile().getMonthlyIncome(),
                userRegisterRequest.getUserProfile().getEducationLevel(),
                userRegisterRequest.getUserProfile().getProfession()
        );
        userProfile.setUser(user);
        user.setUserProfile(userProfile);

        List<Email> emails = userRegisterRequest.getEmails().stream()
                .map(emailDto -> new Email(emailDto.getEmail(), user))
                .collect(Collectors.toList());
        user.setEmails(emails);

        List<Address> addresses = userRegisterRequest.getAddresses().stream()
                .map(addressDto -> new Address(addressDto.getStreet(), addressDto.getCity(), addressDto.getPostalCode(), addressDto.getCountry(), user))
                .collect(Collectors.toList());
        user.setAddresses(addresses);


        List<PhoneNumber> phoneNumbers = userRegisterRequest.getPhoneNumbers().stream()
                .map(phoneNumberDto -> new PhoneNumber(phoneNumberDto.getPhoneNumber(), user))
                .collect(Collectors.toList());
        user.setPhoneNumbers(phoneNumbers);

        Notification userNotification = new Notification(
                userRegisterRequest.getNotificationRegisterRequest().getAccountBalanceUpdate(),
                userRegisterRequest.getNotificationRegisterRequest().getTranscationUpdate(),
                userRegisterRequest.getNotificationRegisterRequest().getSecurityAlert(),
                user
        );
        user.setNotification(userNotification);
        User newUser = userRepository.save(user);
        UserDto convert = userDtoConverter.convert(newUser);

        return convert;
    }

    public UserDto changePassword(ChangePasswordRequest changePasswordRequest) {

        User user = userRepository.findByUserId(changePasswordRequest.getUserId());

        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
            throw GenericExceptionHandler.builder()
                    .errorMessage("Current passwords doesn't match")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .errorCode(ErrorCode.PASSWORD_NOT_MATCH)
                    .build();
        }

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmNewPassword())) {
            throw GenericExceptionHandler.builder()
                    .errorMessage("New password don't match")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .errorCode(ErrorCode.PASSWORD_NOT_MATCH)
                    .build();
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

        User updatedUser = userRepository.save(user);
        return userDtoConverter.convert(updatedUser);
    }
    @Cacheable(CacheNames.USER)
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    @Cacheable(CacheNames.USER)
    public List<User> getAllUser2() {
       return null;
    }

    public UserDto changeTwoFactorAuth(ChangeTwoFactorAuthRequest changeTwoFactorAuthRequest) {
        User user = userRepository.findByUserId(changeTwoFactorAuthRequest.getUserId());

        user.setTwoFactorAuth(changeTwoFactorAuthRequest.getTwoFactorAuth());

        User updatedUser = userRepository.save(user);

        return userDtoConverter.convert(updatedUser);
    }
    @Caching(
            evict = {
                    @CacheEvict(value = CacheNames.USER, allEntries = true)
            }
    )
    public UserDto save(User user) {
        try {
            User save = userRepository.save(user);
            UserDto convert = userDtoConverter.convert(save);
            return convert;
        } catch (Exception ex) {
            throw GenericExceptionHandler.builder()
                    .errorCode(ErrorCode.SOMETHING_WRONG_WHILE_SAVING)
                    .httpStatus(HttpStatus.CONFLICT)
                    .errorMessage("Something wrong while saving.")
                    .build();
        }

    }

}
