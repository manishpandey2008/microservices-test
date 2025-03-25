package com.micro.user.service.payloads;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;

    public ApiResponse(String message, boolean success, HttpStatus status) {
        this.message = message;
        this.success = success;
        this.status = status;
    }
}
