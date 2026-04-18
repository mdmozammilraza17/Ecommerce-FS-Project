package com.ecommerce.user_service.service;

import com.ecommerce.user_service.dto.UserRequestDTO;
import com.ecommerce.user_service.dto.UserResponseDTO;
import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.mapper.UserMapper;
import com.ecommerce.user_service.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        User userEntity = userMapper.toEntity(userRequestDTO);
        userEntity.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        userEntity.setRole("CUSTOMER");


        userRepository.save(userEntity);

        return userMapper.toResponseDTO(userEntity);
    }

    @Override
    public User getUser(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found!!"));
    }
}
