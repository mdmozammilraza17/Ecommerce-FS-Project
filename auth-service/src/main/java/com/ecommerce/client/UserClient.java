package com.ecommerce.client;

import com.ecommerce.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "USER-SERVICE")
public interface UserClient {

    @GetMapping ("/api/users/{username}")
    UserResponseDTO getUserByUsername (@PathVariable String username);
}
