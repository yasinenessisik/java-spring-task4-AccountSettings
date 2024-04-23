package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String userId;
    private String name;
    private String surname;
}
