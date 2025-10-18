package com.usersService.usersService.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
    private String message;
    private boolean success;
    private int status;
    private Object data;
}
