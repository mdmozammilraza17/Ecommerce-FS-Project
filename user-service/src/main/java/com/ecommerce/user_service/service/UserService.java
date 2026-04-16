package com.ecommerce.user_service.service;

import com.ecommerce.user_service.dto.UserRequestDTO;
import com.ecommerce.user_service.dto.UserResponseDTO;
import com.ecommerce.user_service.entity.User;

public interface UserService {

    // Register User
    public UserResponseDTO createUser (UserRequestDTO userRequestDTO);

    // Get User
    public User getUser (String username);
}
