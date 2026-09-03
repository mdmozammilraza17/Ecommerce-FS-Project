package com.ecommerce.dto.registration.resendotp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResendOtpResponse {

    private String message;
    private int expiresIn;
    private int resendAvailableIn;
}
