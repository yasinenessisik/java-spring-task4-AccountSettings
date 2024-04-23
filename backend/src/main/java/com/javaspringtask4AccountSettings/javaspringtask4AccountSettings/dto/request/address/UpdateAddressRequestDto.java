package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAddressRequestDto {
    @NotEmpty(message = "User can not be empty.")
    private String userId;
    @NotNull(message = "Address can not be empty.")
    private Integer addressId;
    @NotBlank(message = "street not be blank.")
    private String street;
    @NotBlank(message = "city not be blank.")
    private String city;
    @NotBlank(message = "postalCode not be blank.")
    private String postalCode;
    @NotBlank(message = "country not be blank.")
    private String country;
}
