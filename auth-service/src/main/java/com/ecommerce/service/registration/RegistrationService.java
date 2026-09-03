package com.ecommerce.service.registration;

import com.ecommerce.dto.registration.*;
import com.ecommerce.dto.registration.resendotp.ResendOtpRequest;

public interface RegistrationService {

    // Create user
    SignupResponse signupUser (SignupRequest signupRequest);

    // Verify OTP
    VerifyOtpResponse verifyOtp (VerifyOtpRequest request);

    // Resend OTP
    void resendOtp (ResendOtpRequest resendOtpRequest);

}
