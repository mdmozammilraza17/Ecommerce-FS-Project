package com.ecommerce.controller;

import com.ecommerce.dto.AuthRequestDTO;
import com.ecommerce.dto.AuthResponseDTO;
import com.ecommerce.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/auth")
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    public AuthResponseDTO getToken(@RequestBody AuthRequestDTO authRequestDTO) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getUsername(),
                        authRequestDTO.getPassword()
                )
        );

        if (authenticate.isAuthenticated()) {

            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

            String role = userDetails.getAuthorities()
                    .stream()
                    .findFirst()
                    .get()
                    .getAuthority()
                    .replace("ROLE_", ""); // ADMIN / USER

            String token = authService.generateToken(userDetails.getUsername(), role);

            return new AuthResponseDTO(token, userDetails.getUsername(), role);
        } else {
            throw new RuntimeException("credential invalid");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        try {
            authService.validateToken(token);
            return "Valid Token ✅";
        } catch (Exception e) {
            return "Invalid Token ❌";
        }
    }
    @GetMapping ("/get")
    public String getAuth ()
    {
        return "This is auth controller";
    }
}

