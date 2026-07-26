package com.ecommerce.service.registration;

import com.ecommerce.dto.registration.SignupRequest;
import com.ecommerce.dto.registration.SignupResponse;
import com.ecommerce.dto.registration.VerifyOtpRequest;
import com.ecommerce.dto.registration.VerifyOtpResponse;

public interface RegistrationService {

    // Create user
    SignupResponse signupUser (SignupRequest signupRequest);

    // Verify OTP
    VerifyOtpResponse verifyOtp (VerifyOtpRequest request);

}
