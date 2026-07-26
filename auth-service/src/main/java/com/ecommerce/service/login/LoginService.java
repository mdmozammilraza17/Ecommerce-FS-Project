package com.ecommerce.service.login;

import com.ecommerce.dto.login.LoginRequestDTO;
import com.ecommerce.dto.login.LoginResponseDTO;

public interface LoginService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
