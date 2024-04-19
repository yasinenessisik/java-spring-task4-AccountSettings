package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address;

import lombok.Data;

@Data
public class UpdateAddressRequestDto {
    private String userId;
    private Integer addressId;

    private String street;

    private String city;

    private String postalCode;

    private String country;
}
