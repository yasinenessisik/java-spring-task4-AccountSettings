package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.EmailDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.PhoneNumberDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberDtoConverter {
    public PhoneNumberDto convert(PhoneNumber from){
        return PhoneNumberDto.builder()
                .phoneNumber_Id(from.getPhoneNumber_Id())
                .phoneNumber(from.getPhoneNumber())
                .build();
    }
}
