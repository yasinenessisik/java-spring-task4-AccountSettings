package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.AddressDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoConverter {
    public AddressDto convert(Address from){
        return AddressDto.builder()
                .address_Id(from.getAddress_Id())
                .street(from.getStreet())
                .city(from.getCity())
                .postalCode(from.getPostalCode())
                .country(from.getCountry())
                .build();
    }
}
