package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.AddressDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.AddressDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.DeleteAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.GetAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.SaveAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.UpdateAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.ErrorCode;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.GenericExceptionHandler;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Address;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDtoConverter addressDtoConverter;
    private final UserService userService;

    public AddressService(AddressRepository addressRepository, AddressDtoConverter addressDtoConverter, UserService userService) {
        this.addressRepository = addressRepository;
        this.addressDtoConverter = addressDtoConverter;
        this.userService = userService;
    }

    public AddressDto getAddressByAddressId(Integer addressId){
        return addressDtoConverter.convert(addressRepository.findById(addressId).orElseThrow(()-> GenericExceptionHandler.builder()
                .errorMessage("Address Not Found")
                .errorCode(ErrorCode.ADDRESS_NOT_FOUND)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build()));
    }
    public List<Address> saveAllAddress(List<Address> address){
        return addressRepository.saveAll(address);
    }
    public UserDto saveAddress(SaveAddressRequestDto from){
        User user = userService.getUserByUserId(from.getUserId());

        user.getAddresses().add(new Address(
                from.getStreet(),
                from.getCity(),
                from.getPostalCode(),
                from.getCountry(),
                user
        ));

        UserDto updatedUser = userService.save(user);
        return updatedUser;
    }
    public UserDto deleteAddress(DeleteAddressRequestDto from) {
        User user = userService.getUserByUserId(from.getUserId());

        Address addressToDelete = null;
        for (Address address : user.getAddresses()) {
            if (address.getAddress_Id().equals(from.getAddressId())) {
                addressToDelete = address;
                break;
            }
        }

        if (addressToDelete != null) {
            user.getAddresses().remove(addressToDelete);
        } else {
            throw new GenericExceptionHandler(HttpStatus.NOT_FOUND,ErrorCode.ADDRESS_NOT_FOUND,"Address Not Found.");
        }
        UserDto updatedUser = userService.save(user);
        return updatedUser;
    }
    public UserDto updateAddress(UpdateAddressRequestDto from) {
        User user = userService.getUserByUserId(from.getUserId());

        Address addressToUpdate = null;
        for (Address address : user.getAddresses()) {
            if (address.getAddress_Id().equals(from.getAddressId())) {
                addressToUpdate = address;
                break;
            }
        }

        if (addressToUpdate != null) {
            addressToUpdate.setStreet(from.getStreet());
            addressToUpdate.setCity(from.getCity());
            addressToUpdate.setPostalCode(from.getPostalCode());
            addressToUpdate.setCountry(from.getCountry());

            addressRepository.save(addressToUpdate);
        } else {
            throw new GenericExceptionHandler(HttpStatus.NOT_FOUND, ErrorCode.ADDRESS_NOT_FOUND, "Address Not Found.");
        }

        UserDto updatedUser = userService.save(user);
        return updatedUser;
    }
    public AddressDto getAddress(GetAddressRequestDto from) {
        User user = userService.getUserByUserId(from.getUserId());

        Address addressRequest = null;
        try {
            for (Address address : user.getAddresses()) {
                if (address.getAddress_Id().equals(from.getAddressId())) {
                    addressRequest = address;
                    break;
                }
            }
            return addressDtoConverter.convert(addressRequest);
        }catch (Exception e){
            throw new GenericExceptionHandler(HttpStatus.NOT_FOUND, ErrorCode.ADDRESS_NOT_FOUND, "Address Not Found.");

        }
    }
    public List<AddressDto> getAllAddress(String userId) {
        User user = userService.getUserByUserId(userId);
        return user.getAddresses().stream().map(address -> addressDtoConverter.convert(address)).collect(Collectors.toList());
    }
}
