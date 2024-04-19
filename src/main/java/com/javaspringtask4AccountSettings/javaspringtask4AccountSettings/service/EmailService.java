package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.EmailDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.PhoneNumberDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.EmailDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.email.DeleteEmailRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.email.SaveEmailRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.phonenumber.DeletePhoneNumberRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.phonenumber.SavePhoneNumberRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.ErrorCode;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.GenericExceptionHandler;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Address;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Email;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.PhoneNumber;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.EmailRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService {
    private final EmailDtoConverter emailDtoConverter;
    private final UserService userService;
    private final EmailRepository emailRepository;

    public EmailService(EmailDtoConverter emailDtoConverter, UserService userService, EmailRepository emailRepository) {
        this.emailDtoConverter = emailDtoConverter;
        this.userService = userService;
        this.emailRepository = emailRepository;
    }

    public List<Email> saveAllEmail(List<Email> emails){
        return emailRepository.saveAll(emails);
    }
    public UserDto saveEmail(SaveEmailRequest from){
        User user = userService.getUserByUserId(from.getUserId());

        user.getEmails().add(new Email(
                from.getEmail(),
                user
        ));

        UserDto updatedUser = userService.save(user);
        return updatedUser;
    }
    public UserDto deleteEmail(DeleteEmailRequest from) {
        User user = userService.getUserByUserId(from.getUserId());

        Email emailToDelete = null;
        for (Email email : user.getEmails()) {
            if (email.getEmailId().equals(from.getEmailId())) {
                emailToDelete = email;
                break;
            }
        }

        if (emailToDelete != null) {
            user.getEmails().remove(emailToDelete);
        } else {
            throw new GenericExceptionHandler(HttpStatus.NOT_FOUND,ErrorCode.EMAIL_NOT_FOUND,"Email Not Found.");
        }
        UserDto updatedUser = userService.save(user);
        return updatedUser;
    }
    public List<EmailDto> getAllEmail(String userId) {
        User user = userService.getUserByUserId(userId);
        return user.getEmails().stream().map(email -> emailDtoConverter.convert(email)).collect(Collectors.toList());
    }
}
