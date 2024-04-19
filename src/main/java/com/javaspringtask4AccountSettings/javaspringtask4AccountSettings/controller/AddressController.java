package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.controller;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.AddressDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.DeleteAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.GetAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.SaveAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.UpdateAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/controller/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PatchMapping("updateAddress")
    public UserDto updateAddress(@RequestBody UpdateAddressRequestDto from) {
        return addressService.updateAddress(from);
    }
    @DeleteMapping("deleteAddress")
    public UserDto updateAddress(@RequestBody DeleteAddressRequestDto from) {
        return addressService.deleteAddress(from);
    }

    @PostMapping("saveAddress")
    public UserDto saveAddress(@RequestBody SaveAddressRequestDto saveAddressRequestDto){
        return addressService.saveAddress(saveAddressRequestDto);
    }
    @PostMapping("getAddress")
    public AddressDto getAddress(@RequestBody GetAddressRequestDto getAddressRequestDto){
        return addressService.getAddress(getAddressRequestDto);
    }
    @GetMapping("/{userId}/getAllAddress")
    public List<AddressDto> getAllAddress(@PathVariable String userId) {
        return addressService.getAllAddress(userId);
    }
}
