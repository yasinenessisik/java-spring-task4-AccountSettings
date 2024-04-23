package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.controller;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.EmailDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.email.DeleteEmailRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.email.SaveEmailRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<EmailDto>  deleteEmail(@Valid @RequestBody DeleteEmailRequest from) {
        return ResponseEntity.ok(emailService.deleteEmail(from));
    }

    @PostMapping("saveEmail")
    public ResponseEntity<EmailDto> saveEmail(@Valid @RequestBody SaveEmailRequest from){
        return ResponseEntity.ok(emailService.saveEmail(from));
    }
    @GetMapping("/{userId}/getAllEmail")
    public List<EmailDto> getAllEmail(@PathVariable String userId) {
        return emailService.getAllEmail(userId);
    }
}
