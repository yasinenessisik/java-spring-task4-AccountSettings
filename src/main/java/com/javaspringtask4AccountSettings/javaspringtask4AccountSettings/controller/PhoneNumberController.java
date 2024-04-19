package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.controller;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.AddressDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.PhoneNumberDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.DeleteAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.GetAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.SaveAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.UpdateAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.phonenumber.DeletePhoneNumberRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.phonenumber.SavePhoneNumberRequest;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.PhoneNumberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/controller/phoneNumber")
public class PhoneNumberController {
    private final PhoneNumberService phoneNumberService;

    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @DeleteMapping("deletePhoneNumber")
    public UserDto updateAddress(@RequestBody DeletePhoneNumberRequest from) {
        return phoneNumberService.deletePhoneNumber(from);
    }

    @PostMapping("savePhoneNumber")
    public UserDto saveAddress(@RequestBody SavePhoneNumberRequest savePhoneNumberRequest){
        return phoneNumberService.savePhoneNumber(savePhoneNumberRequest);
    }
    @GetMapping("/{userId}/getAllPhoneNumber")
    public List<PhoneNumberDto> getAllAddress(@PathVariable String userId) {
        return phoneNumberService.getAllPhoneNumber(userId);
    }
}
