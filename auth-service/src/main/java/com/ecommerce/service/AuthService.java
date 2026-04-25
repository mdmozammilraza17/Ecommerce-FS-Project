package com.ecommerce.service;

import com.ecommerce.client.UserClient;
import com.ecommerce.util.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;

    private final UserClient userClient;

    public AuthService(JwtService jwtService, UserClient userClient) {
        this.jwtService = jwtService;
        this.userClient = userClient;
    }

    public String generateToken (String username, String role)
    {
        return jwtService.generateToken(username, role);
    }

    public void validateToken (String token)
    {
        jwtService.validateToken(token);
    }
}
