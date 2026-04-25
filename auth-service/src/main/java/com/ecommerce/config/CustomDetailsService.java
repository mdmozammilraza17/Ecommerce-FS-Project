package com.ecommerce.config;

import com.ecommerce.client.UserClient;
import com.ecommerce.dto.UserResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomDetailsService implements UserDetailsService {

    // Feign Interface to fetch the username
    private final UserClient userClient;

    public CustomDetailsService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserResponseDTO user = userClient.getUserByUsername(username);

            return new CustomUserDetails(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRole()
            );

        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found: "+username);
        }
    }
}
