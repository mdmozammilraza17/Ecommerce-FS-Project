package com.ecommerce.service.registration;

import com.ecommerce.entity.registration.UserEntity;

public interface EmailOtpService {


    void generateAndSaveOtp(UserEntity user);

    void verifyOtp(UserEntity user, String otp);
}
