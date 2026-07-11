package com.ecommerce.util;

import java.security.SecureRandom;

public final class OtpUtil {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final int OTP_LENGTH = 6;

    private OtpUtil() {
        // Prevent object creation
    }

    public static String generateOtp() {

        int bound = (int) Math.pow(10, OTP_LENGTH);
        int min = bound / 10;

        int otp = SECURE_RANDOM.nextInt(bound - min) + min;

        return String.valueOf(otp);
    }
}