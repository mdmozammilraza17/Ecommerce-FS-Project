package com.ecommerce.service.refreshToken;

import com.ecommerce.dto.refreshToken.RefreshTokenResponseDTO;
import com.ecommerce.entity.refreshToken.RefreshToken;
import com.ecommerce.entity.registration.UserEntity;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(UserEntity user);

    RefreshTokenResponseDTO refreshTokenDto(String token);

    void revokeRefreshToken(String token);
}
