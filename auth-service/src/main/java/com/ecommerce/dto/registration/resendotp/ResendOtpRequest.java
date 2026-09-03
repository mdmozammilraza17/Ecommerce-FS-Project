package com.ecommerce.dto.registration.resendotp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResendOtpRequest {

    private String emailAddress;
}
