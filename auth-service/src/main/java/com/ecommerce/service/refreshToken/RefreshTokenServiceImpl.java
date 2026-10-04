package com.ecommerce.service.refreshToken;

import com.ecommerce.dto.refreshToken.RefreshTokenResponseDTO;
import com.ecommerce.entity.refreshToken.RefreshToken;
import com.ecommerce.entity.registration.UserEntity;
import com.ecommerce.repository.refreshToken.RefreshTokenRepository;
import com.ecommerce.repository.registration.UserRepository;
import com.ecommerce.security.CustomUserDetailsService;
import com.ecommerce.security.jwt.JwtService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public RefreshToken createRefreshToken(UserEntity user) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setToken(generateRandomToken());

        // 7 DAYS
        refreshToken.setExpiryDate(
                Instant.now().plus(7, ChronoUnit.DAYS)
        );

        refreshToken.setRevoked(false);

        refreshToken.setUserEntity(user);

        refreshToken.setCreatedAt(Instant.now());
        refreshToken.setUpdatedAt(Instant.now());

        return refreshTokenRepository.save(refreshToken);

    }

    @Override
    public RefreshTokenResponseDTO refreshTokenDto(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));


        if (refreshToken.isRevoked())
        {
            throw new RuntimeException("Refresh token revoked");
        }

        if (refreshToken.getExpiryDate().isBefore(Instant.now()))
        {
            throw new RuntimeException("Refresh token expired");
        }

        UserEntity user = refreshToken.getUserEntity();

        UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(user.getEmailAddress());

        String accessToken = jwtService.generateToken(userDetails);

        RefreshTokenResponseDTO response = new RefreshTokenResponseDTO();

        response.setAccessToken(accessToken);

        return response;

    }

    @Override
    public void revokeRefreshToken(String token) {
        RefreshToken refreshToken =
                refreshTokenRepository.findByToken(token)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Refresh token not found"
                                ));

        refreshToken.setRevoked(true);
        refreshToken.setUpdatedAt(Instant.now());

        refreshTokenRepository.save(refreshToken);
    }

    private String generateRandomToken() {

        byte[] randomBytes = new byte[64];

        SecureRandom secureRandom =
                new SecureRandom();

        secureRandom.nextBytes(randomBytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(randomBytes);
    }
}
