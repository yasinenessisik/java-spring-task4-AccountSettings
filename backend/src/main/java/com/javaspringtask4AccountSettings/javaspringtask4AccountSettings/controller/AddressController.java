package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.controller;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.AddressDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.DeleteAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.GetAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.SaveAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.UpdateAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AddressDto>  updateAddress(@Valid @RequestBody UpdateAddressRequestDto from) {
        return ResponseEntity.ok(addressService.updateAddress(from));
    }
    @DeleteMapping("deleteAddress")
    public ResponseEntity<AddressDto>  deleteAddress(@Valid @RequestBody DeleteAddressRequestDto from) {
        return ResponseEntity.ok(addressService.deleteAddress(from));
    }

    @PostMapping("saveAddress")
    public ResponseEntity<AddressDto> saveAddress(@Valid @RequestBody SaveAddressRequestDto saveAddressRequestDto){
        return ResponseEntity.ok(addressService.saveAddress(saveAddressRequestDto));
    }
    @PostMapping("getAddress")
    public ResponseEntity<AddressDto> getAddress(@RequestBody GetAddressRequestDto getAddressRequestDto){
        return ResponseEntity.ok(addressService.getAddress(getAddressRequestDto));
    }
    @GetMapping("/{userId}/getAllAddress")
    public ResponseEntity<List<AddressDto>> getAllAddress(@PathVariable String userId) {
        return ResponseEntity.ok(addressService.getAllAddress(userId));
    }
}
