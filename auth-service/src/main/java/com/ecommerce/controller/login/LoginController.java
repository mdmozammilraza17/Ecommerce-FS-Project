package com.ecommerce.controller.login;

import com.ecommerce.dto.login.LoginRequestDTO;
import com.ecommerce.dto.login.LoginResponseDTO;
import com.ecommerce.service.login.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // Login API
    @PostMapping ("/login")
    public ResponseEntity<LoginResponseDTO> userLogin (
            @Valid @RequestBody LoginRequestDTO loginRequestDTO)
    {
        LoginResponseDTO login = loginService.login(loginRequestDTO);
        return ResponseEntity.ok(login);
    }
}
