package com.ecommerce.service;

import com.ecommerce.dto.VerifyOtpRequest;
import com.ecommerce.dto.VerifyOtpResponse;
import com.ecommerce.entity.UserEntity;

public interface EmailOtpService {

    void generateAndSaveOtp(UserEntity user);

    void verifyOtp(UserEntity user, String otp);
}
