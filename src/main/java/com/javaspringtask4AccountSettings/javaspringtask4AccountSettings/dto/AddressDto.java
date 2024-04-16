package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private Integer address_Id;

    private String street;

    private String city;

    private String postalCode;

    private String country;
}
