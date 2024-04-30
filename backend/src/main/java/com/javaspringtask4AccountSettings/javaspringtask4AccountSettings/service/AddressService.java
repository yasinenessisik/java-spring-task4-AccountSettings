package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.AddressDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter.AddressDtoConverter;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.DeleteAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.GetAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.SaveAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address.UpdateAddressRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.ErrorCode;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.GenericExceptionHandler;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Address;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.jparepository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDtoConverter addressDtoConverter;
    private final UserService userService;

    public AddressService(AddressRepository addressRepository, AddressDtoConverter addressDtoConverter, UserService userService) {
        this.addressRepository = addressRepository;
        this.addressDtoConverter = addressDtoConverter;
        this.userService = userService;
    }

    public AddressDto getAddressByAddressId(Integer addressId) {
        return addressDtoConverter.convert(addressRepository.findById(addressId).orElseThrow(() -> {
                    log.error("At Address Service getAddressByAddressId method Address Not Found :" + addressId);
                    return GenericExceptionHandler.builder()
                            .errorMessage("Address Not Found")
                            .errorCode(ErrorCode.ADDRESS_NOT_FOUND)
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build();
                }
        ));
    }

    public List<Address> saveAllAddress(List<Address> address) {
        return addressRepository.saveAll(address);
    }

    public AddressDto saveAddress(SaveAddressRequestDto from) {
        User user = userService.getUserByUserId(from.getUserId());

        Address address = new Address(
                from.getStreet(),
                from.getCity(),
                from.getPostalCode(),
                from.getCountry(),
                user
        );
        user.getAddresses().add(address);

        User updatedUser = userService.save(user);
        return addressDtoConverter.convert(address);
    }

    public AddressDto deleteAddress(DeleteAddressRequestDto from) {
        log.info(from.getUserId());
        log.info(String.valueOf(from.getAddressId()));
        User user = userService.getUserByUserId(from.getUserId());
        Address addressToDelete = null;
        for (Address address : user.getAddresses()) {
            if (address.getAddress_Id() == from.getAddressId()) {
                addressToDelete = address;
                break;
            }
        }
        if (addressToDelete != null) {
            user.getAddresses().remove(addressToDelete);
        } else {
            log.error("At Address Service deleteAddrress method : ADDRESS_NOT_FOUND");
            throw new GenericExceptionHandler(HttpStatus.NOT_FOUND, ErrorCode.ADDRESS_NOT_FOUND, "Address Not Found.");
        }
        User updatedUser = userService.save(user);
        addressRepository.delete(addressToDelete);
        return addressDtoConverter.convert(addressToDelete);
    }

    public AddressDto updateAddress(UpdateAddressRequestDto from) {
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

        User updatedUser = userService.save(user);
        return addressDtoConverter.convert(addressToUpdate);
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
        } catch (Exception e) {
            log.error("At Address Service getAddress method :"+e.getMessage());
            throw new GenericExceptionHandler(HttpStatus.NOT_FOUND, ErrorCode.ADDRESS_NOT_FOUND, "Address Not Found.");

        }
    }

    public List<AddressDto> getAllAddress(String userId) {
        User user = userService.getUserByUserId(userId);
        return user.getAddresses().stream().map(address -> addressDtoConverter.convert(address)).collect(Collectors.toList());
    }
}
