package com.ecommerce.service;

import com.ecommerce.client.UserClient;
import com.ecommerce.config.PasswordConfig;
import com.ecommerce.dto.UserResponseDTO;
import com.ecommerce.exception.InvalidCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthUserDetailsService implements UserDetailsService {

    // Feign Interface for user-service
    private final UserClient userClient;

    private final PasswordConfig passwordConfig;

    public AuthUserDetailsService(UserClient userClient, PasswordConfig passwordConfig) {
        this.userClient = userClient;
        this.passwordConfig = passwordConfig;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Fetch from user-service of user details by username
        UserResponseDTO user;
        try
        {
            user = userClient.getUserByUsername(username);
        }catch (Exception e)
        {
            throw new InvalidCredentialsException("User not found: "+username);
        }

        if (user == null)
        {
            throw new InvalidCredentialsException("Username not found: "+username);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority(user.getRole()))
                .build();
    }
}
