package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.AddressDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.AddressDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.ErrorCode;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.GenericExceptionHandler;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Address;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDtoConverter addressDtoConverter;

    public AddressService(AddressRepository addressRepository, AddressDtoConverter addressDtoConverter) {
        this.addressRepository = addressRepository;
        this.addressDtoConverter = addressDtoConverter;
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

}
