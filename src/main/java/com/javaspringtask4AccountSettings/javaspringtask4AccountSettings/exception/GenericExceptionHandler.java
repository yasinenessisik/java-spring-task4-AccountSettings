package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class GenericExceptionHandler extends RuntimeException {
    private HttpStatus httpStatus;
    private ErrorCode errorCode;
    private String errorMessage;
}