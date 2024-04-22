package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.EmailDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailDtoConverter {
    public EmailDto convert(Email from){
        return EmailDto.builder()
                .emailId(from.getEmailId())
                .email(from.getEmail())
                .build();
    }
}
