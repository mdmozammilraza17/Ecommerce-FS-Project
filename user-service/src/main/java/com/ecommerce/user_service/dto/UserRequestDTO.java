package com.ecommerce.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotBlank (message = "username is required")
    private String username;

    @NotBlank (message = "password is required")
    private String password;
}
