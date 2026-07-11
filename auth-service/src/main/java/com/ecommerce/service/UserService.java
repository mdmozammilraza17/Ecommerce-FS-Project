package com.ecommerce.service;

import com.ecommerce.dto.SignupRequest;
import com.ecommerce.dto.SignupResponse;
import com.ecommerce.dto.VerifyOtpRequest;
import com.ecommerce.dto.VerifyOtpResponse;

public interface UserService {

    // Create user
    SignupResponse signupUser (SignupRequest signupRequest);

    // Verify OTP
    VerifyOtpResponse verifyOtp (VerifyOtpRequest request);

}
