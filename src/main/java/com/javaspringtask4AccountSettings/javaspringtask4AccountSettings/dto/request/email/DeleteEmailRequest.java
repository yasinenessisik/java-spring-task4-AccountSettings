package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.email;

import lombok.Data;

@Data
public class DeleteEmailRequest {
    private String userId;
    private Integer emailId;
}
