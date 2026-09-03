package com.ecommerce.controller.registration;

import com.ecommerce.dto.registration.*;
import com.ecommerce.dto.registration.resendotp.ResendOtpRequest;
import com.ecommerce.dto.registration.resendotp.ResendOtpResponse;
import com.ecommerce.service.registration.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/auth")
public class RegistrationController {

    private final RegistrationService userService;

    public RegistrationController(RegistrationService userService) {
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

    // Resend OTP
    @PostMapping ("/resend-otp")
    public ResponseEntity<ResendOtpResponse> resendOtpRequest (
            @RequestBody ResendOtpRequest resendOtpRequest)
    {
        userService.resendOtp(resendOtpRequest);
        ResendOtpResponse resendOtpResponse = new ResendOtpResponse(
                "OTP sent successfully",
                300,
                60
        );
        return ResponseEntity.ok(resendOtpResponse);
    }
}

