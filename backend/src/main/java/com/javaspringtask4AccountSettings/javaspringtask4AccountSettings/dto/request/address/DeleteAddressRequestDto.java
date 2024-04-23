package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteAddressRequestDto {
    @NotEmpty(message = "User can not be empty.")
    private String userId;
    @NotNull(message = "Address can not be empty.")
    private Integer addressId;
}
