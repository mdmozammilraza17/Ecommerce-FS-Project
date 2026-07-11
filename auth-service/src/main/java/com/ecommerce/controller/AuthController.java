package com.ecommerce.controller;

import com.ecommerce.dto.SignupRequest;
import com.ecommerce.dto.SignupResponse;
import com.ecommerce.dto.VerifyOtpRequest;
import com.ecommerce.dto.VerifyOtpResponse;
import com.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Creating User
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signupUser (@Valid @RequestBody SignupRequest signupRequest)
    {

        SignupResponse signupResponse = userService.signupUser(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(signupResponse);
    }

    // Verify OTP
    @PostMapping ("/verify-otp")
    public ResponseEntity<VerifyOtpResponse> verifyOtpResponse (@Valid @RequestBody VerifyOtpRequest request)
    {
        VerifyOtpResponse verifyOtpResponse = userService.verifyOtp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(verifyOtpResponse);
    }
}

