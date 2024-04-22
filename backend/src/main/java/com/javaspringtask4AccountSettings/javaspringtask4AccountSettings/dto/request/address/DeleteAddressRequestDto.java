package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.address;

import lombok.Data;

@Data
public class DeleteAddressRequestDto {
    private String userId;
    private Integer addressId;
}
