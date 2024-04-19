package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.controller;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.EmailDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.PhoneNumberDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.email.DeleteEmailRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.email.SaveEmailRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.phonenumber.DeletePhoneNumberRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.phonenumber.SavePhoneNumberRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/controller/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @DeleteMapping("deleteEmail")
    public UserDto deleteEmail(@RequestBody DeleteEmailRequest from) {
        return emailService.deleteEmail(from);
    }

    @PostMapping("saveEmail")
    public UserDto saveEmail(@RequestBody SaveEmailRequest from){
        return emailService.saveEmail(from);
    }
    @GetMapping("/{userId}/getAllPhoneNumber")
    public List<EmailDto> getAllEmail(@PathVariable String userId) {
        return emailService.getAllEmail(userId);
    }
}
