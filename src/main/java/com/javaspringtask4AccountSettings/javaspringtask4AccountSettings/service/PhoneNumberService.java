package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.PhoneNumberDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.PhoneNumberDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.phonenumber.DeletePhoneNumberRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.phonenumber.SavePhoneNumberRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.ErrorCode;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.GenericExceptionHandler;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.PhoneNumber;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.jparepository.PhoneNumberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneNumberService {
    private final PhoneNumberDtoConverter phoneNumberDtoConverter;
    private final PhoneNumberRepository phoneNumberRepository;
    private final UserService userService;

    public PhoneNumberService(PhoneNumberDtoConverter phoneNumberDtoConverter, PhoneNumberRepository phoneNumberRepository, UserService userService) {
        this.phoneNumberDtoConverter = phoneNumberDtoConverter;
        this.phoneNumberRepository = phoneNumberRepository;
        this.userService = userService;
    }

    public List<PhoneNumber> saveAllPhoneNumber(List<PhoneNumber> phoneNumber) {
        return phoneNumberRepository.saveAll(phoneNumber);
    }

    public PhoneNumberDto savePhoneNumber(SavePhoneNumberRequest from) {
        User user = userService.getUserByUserId(from.getUserId());
        PhoneNumber phoneNumber = new PhoneNumber(
                from.getPhoneNumber(),
                user);
        user.getPhoneNumbers().add(phoneNumber);

        UserDto updatedUser = userService.save(user);
        return phoneNumberDtoConverter.convert(phoneNumber);
    }

    public PhoneNumberDto deletePhoneNumber(DeletePhoneNumberRequest from) {
        User user = userService.getUserByUserId(from.getUserId());

        PhoneNumber phoneNumberToDelete = null;
        for (PhoneNumber phoneNumber : user.getPhoneNumbers()) {
            if (phoneNumber.getPhoneNumber_Id().equals(from.getPhoneNumberId())) {
                phoneNumberToDelete = phoneNumber;
                break;
            }
        }

        if (phoneNumberToDelete != null) {
            user.getPhoneNumbers().remove(phoneNumberToDelete);
        } else {
            throw new GenericExceptionHandler(HttpStatus.NOT_FOUND, ErrorCode.ADDRESS_NOT_FOUND, "Address Not Found.");
        }

        UserDto updatedUser = userService.save(user);
        return phoneNumberDtoConverter.convert(phoneNumberToDelete);
    }

    public List<PhoneNumberDto> getAllPhoneNumber(String userId) {
        User user = userService.getUserByUserId(userId);
        return user.getPhoneNumbers().stream().map(phoneNumber -> phoneNumberDtoConverter.convert(phoneNumber)).collect(Collectors.toList());
    }
}
