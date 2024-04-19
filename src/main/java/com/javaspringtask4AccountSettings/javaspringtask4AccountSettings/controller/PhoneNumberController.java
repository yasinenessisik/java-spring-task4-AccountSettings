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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PhoneNumberDto> deletePhoneNumber(@RequestBody DeletePhoneNumberRequest from) {
        return ResponseEntity.ok(phoneNumberService.deletePhoneNumber(from));
    }

    @PostMapping("savePhoneNumber")
    public ResponseEntity<PhoneNumberDto> savePhoneNumber(@RequestBody SavePhoneNumberRequest savePhoneNumberRequest){
        return ResponseEntity.ok(phoneNumberService.savePhoneNumber(savePhoneNumberRequest));
    }
    @GetMapping("/{userId}/getAllPhoneNumber")
    public List<PhoneNumberDto> getAllPhoneNumber(@PathVariable String userId) {
        return phoneNumberService.getAllPhoneNumber(userId);
    }
}
