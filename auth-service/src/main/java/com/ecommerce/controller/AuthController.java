package com.ecommerce.controller;

import com.ecommerce.config.PasswordConfig;
import com.ecommerce.dto.AuthResponseDTO;
import com.ecommerce.dto.UserRequestDTO;
import com.ecommerce.service.AuthUserDetailsService;
import com.ecommerce.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/auth")
public class AuthController {

    private final AuthUserDetailsService authUserDetailsService;
    private final PasswordConfig passwordConfig;
    private final JwtUtil jwtUtil;

    public AuthController(AuthUserDetailsService authUserDetailsService, PasswordConfig passwordConfig, JwtUtil jwtUtil) {
        this.authUserDetailsService = authUserDetailsService;
        this.passwordConfig = passwordConfig;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody UserRequestDTO request) {

        UserDetails userDetails =
                authUserDetailsService.loadUserByUsername(request.getUsername());

        if (!passwordConfig.passwordEncoder().matches(
                request.getPassword(),
                userDetails.getPassword()
        )) {
            throw new RuntimeException("Invalid password!");
        }

        String role = userDetails.getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        String token = jwtUtil.generateToken(
                userDetails.getUsername(),
                role
        );

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        response.setUsername(userDetails.getUsername());
        response.setRole(role);

        return ResponseEntity.ok(response);
    }


    @GetMapping ("/get")
    public String getAuth ()
    {
        return "This is auth controller";
    }
}

