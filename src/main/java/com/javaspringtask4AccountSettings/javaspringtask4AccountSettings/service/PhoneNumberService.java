package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.PhoneNumberDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.PhoneNumberDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.PhoneNumber;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.PhoneNumberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberService {
    private final PhoneNumberDtoConverter phoneNumberDtoConverter;
    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberService(PhoneNumberDtoConverter phoneNumberDtoConverter, PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberDtoConverter = phoneNumberDtoConverter;
        this.phoneNumberRepository = phoneNumberRepository;
    }
    public List<PhoneNumber> saveAllPhoneNumber(List<PhoneNumber> phoneNumber){
        return phoneNumberRepository.saveAll(phoneNumber);
    }
}
