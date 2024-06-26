package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.UserDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.notification.ChangeTwoFactorAuthRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.password.ChangePasswordRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.register.UserRegisterRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.response.TwoFactoAuthResponse;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.response.UserResponse;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.ErrorCode;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.GenericExceptionHandler;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.*;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.jparepository.UserRepository;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.redisrepository.UserRedisRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final PasswordEncoder passwordEncoder;
    private final UserRedisRepository userRedisRepository;


    protected User getUserByUserId(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
                    log.error("At User Service getUserByUserId method User not found :"+ userId);
                    return GenericExceptionHandler.builder()
                    .errorCode(ErrorCode.USER_NOT_FOUND)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .errorMessage("User Not Found.")
                    .build();
        }
        );
    }

    public UserDto getUserByUserIdPublic(String userId) {
        Optional<UserDto> cachedUser = userRedisRepository.findById(userId);
        if (cachedUser.isPresent()) {
            log.info("Data come from Redis");
            return cachedUser.get();
        }
        User user = userRepository.findById(userId).orElseThrow(() -> GenericExceptionHandler.builder()
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorMessage("User Not Found.")
                .build());
        UserDto convertedUser = userDtoConverter.convert(user);
        log.info("Data come from MySql");
        convertedUser.setTimeToLive(60L);
        userRedisRepository.save(convertedUser);
        return convertedUser;
    }

    @Transactional
    public UserDto saveUser(UserRegisterRequest userRegisterRequest) {

        Boolean twoFactorAuth = userRegisterRequest.getTwoFactorAuth();
        Boolean isEnabledNotification = userRegisterRequest.getIsEnabledNotification();
        String firstName = userRegisterRequest.getFirstName();
        String lastName = userRegisterRequest.getLastName();
        String uniquePath = UUID.randomUUID().toString();

        User user = new User();
        user.setProfilePhotoPath(uniquePath + ".jpg");
        String encode = passwordEncoder.encode(userRegisterRequest.getPassword());
        user.setPassword(encode);
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
        User newUser = save(user);
        return userDtoConverter.convert(newUser);
    }

    public UserDto changePassword(ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findByUserId(changePasswordRequest.getUserId());


        if (!isValidNewPassword(changePasswordRequest, user.getPassword())) {
            throw GenericExceptionHandler.builder()
                    .errorMessage("New password is not valid")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .errorCode(ErrorCode.PASSWORD_NOT_MATCH)
                    .build();
        }

        String encode = passwordEncoder.encode(changePasswordRequest.getNewPassword());
        user.setPassword(encode);
        User updatedUser = save(user);
        return userDtoConverter.convert(updatedUser);
    }
    private boolean isValidNewPassword(ChangePasswordRequest changePasswordRequest, String currentUserPasswordHash) {
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), currentUserPasswordHash)) {
            throw GenericExceptionHandler.builder()
                    .errorMessage("Current password is incorrect")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .errorCode(ErrorCode.PASSWORD_NOT_MATCH)
                    .build();
        }

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmNewPassword())) {
            throw GenericExceptionHandler.builder()
                    .errorMessage("New passwords don't match")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .errorCode(ErrorCode.PASSWORD_NOT_MATCH)
                    .build();
        }
        return true;
    }
    private boolean areLastThreePasswordsDifferent(List<String> lastThreePasswordHashes, String newPassword) {
        String newPasswordHash = passwordEncoder.encode(newPassword);

        for (String passwordHash : lastThreePasswordHashes) {
            if (passwordEncoder.matches(newPasswordHash, passwordHash)) {
                return false;
            }
        }
        return true;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public UserDto changeTwoFactorAuth(ChangeTwoFactorAuthRequest changeTwoFactorAuthRequest) {
        User user = userRepository.findByUserId(changeTwoFactorAuthRequest.getUserId());

        user.setTwoFactorAuth(changeTwoFactorAuthRequest.getTwoFactorAuth());

        User updatedUser = save(user);

        return userDtoConverter.convert(updatedUser);
    }

    public User save(User user) {
        try {
            if (user.getUserId() != null) {
                userRedisRepository.deleteById(user.getUserId());
            }
            User save = userRepository.save(user);
            return save;
        } catch (Exception ex) {
            log.error("At User Service save method :"+ex.getMessage());
            String errorMessage = ex.getMessage();
            String fieldName = null;
            if(ex.getMessage().contains("Duplicate entry")){
                if (errorMessage.contains("email")) {
                    fieldName = "E-mail";
                } else if (errorMessage.contains("phone_number")) {
                    fieldName = "Phone Number";
                }
                throw GenericExceptionHandler.builder()
                        .errorCode(ErrorCode.SOMETHING_WRONG_WHILE_SAVING)
                        .httpStatus(HttpStatus.CONFLICT)
                        .errorMessage("Please Enter Unique Value for : "+fieldName)
                        .build();
            }
            else {
                log.error("At User Service save method :"+ex.getMessage());
                throw GenericExceptionHandler.builder()
                        .errorCode(ErrorCode.SOMETHING_WRONG_WHILE_SAVING)
                        .httpStatus(HttpStatus.CONFLICT)
                        .errorMessage(ex.getMessage())
                        .build();
            }
        }

    }

    public TwoFactoAuthResponse getTwoFactorAuth(String userId) {
        User user = userRepository.findByUserId(userId);
        return TwoFactoAuthResponse.builder()
                .twoFactorAuth(user.getTwoFactorAuth())
                .build();
    }

    public void resetCache(String userId) {
        userRedisRepository.deleteById(userId);
    }

    public UserResponse getUserByUserIdMainScreen(String userId) {
        User user = getUserByUserId(userId);
        return UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getFirstName())
                .surname(user.getLastName())
                .build();
    }
}
