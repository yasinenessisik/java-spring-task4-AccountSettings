package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TwoFactoAuthResponse {
    private Boolean twoFactorAuth;
}
