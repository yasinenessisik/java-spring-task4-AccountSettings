package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address;

import lombok.Data;

@Data
public class SaveAddressRequestDto {
    private String userId;

    private String street;

    private String city;

    private String postalCode;

    private String country;
}
