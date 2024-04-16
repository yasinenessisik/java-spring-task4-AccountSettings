package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    private Integer emailId;

    private String email;

}
