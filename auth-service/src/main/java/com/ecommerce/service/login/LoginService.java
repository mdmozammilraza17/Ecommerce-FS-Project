package com.ecommerce.service.login;

import com.ecommerce.dto.login.LoginRequestDTO;
import com.ecommerce.dto.login.LoginResponseDTO;
import com.ecommerce.dto.login.UserProfileResponseDTO;
import com.ecommerce.security.CustomUserDetails;

public interface LoginService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    UserProfileResponseDTO getCurrentUser(CustomUserDetails customUserDetails);
}
