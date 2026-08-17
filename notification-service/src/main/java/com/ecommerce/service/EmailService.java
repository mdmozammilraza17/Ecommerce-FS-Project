package com.ecommerce.service;

public interface EmailService {

    void sendOtpEmail (String firstName, String to, String otp);
}
