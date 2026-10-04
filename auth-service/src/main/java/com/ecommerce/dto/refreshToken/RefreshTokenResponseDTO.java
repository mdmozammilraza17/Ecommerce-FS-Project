package com.ecommerce.dto.refreshToken;

import lombok.Data;

import java.time.Instant;

@Data
public class RefreshTokenResponseDTO {

    private String accessToken;
}
