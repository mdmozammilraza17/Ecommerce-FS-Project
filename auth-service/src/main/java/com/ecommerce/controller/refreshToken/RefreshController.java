package com.ecommerce.controller.refreshToken;

import com.ecommerce.dto.refreshToken.RefreshTokenRequestDTO;
import com.ecommerce.dto.refreshToken.RefreshTokenResponseDTO;
import com.ecommerce.service.refreshToken.RefreshTokenService;
import jakarta.ws.rs.core.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping ("/api/auth")
@RestController
public class RefreshController {

    private final RefreshTokenService refreshTokenService;

    public RefreshController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping ("/refresh")
    public ResponseEntity<RefreshTokenResponseDTO> refreshToken (
            @RequestBody RefreshTokenRequestDTO request)
    {
        RefreshTokenResponseDTO response = refreshTokenService
                .refreshTokenDto(request.getRefreshToken());

        return ResponseEntity.ok(response);
    }
}
