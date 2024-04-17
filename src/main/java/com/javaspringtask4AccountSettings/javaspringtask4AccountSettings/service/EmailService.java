package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.EmailDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.EmailDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Email;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private final EmailDtoConverter emailDtoConverter;
    private final EmailRepository emailRepository;

    public EmailService(EmailDtoConverter emailDtoConverter, EmailRepository emailRepository) {
        this.emailDtoConverter = emailDtoConverter;
        this.emailRepository = emailRepository;
    }

    public List<Email> saveAllEmail(List<Email> emails){
        return emailRepository.saveAll(emails);
    }
}
