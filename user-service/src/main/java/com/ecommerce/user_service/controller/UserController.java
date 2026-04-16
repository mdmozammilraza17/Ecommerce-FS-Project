package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.dto.UserRequestDTO;
import com.ecommerce.user_service.dto.UserResponseDTO;
import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/users")
public class UserController {

    public final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping ("/get")
    public String getUser ()
    {
        return "This is user";
    }

    @PostMapping ("/register")
    public ResponseEntity<UserResponseDTO> createUser (@Valid @RequestBody UserRequestDTO userRequestDTO)
    {
        UserResponseDTO response = userService.createUser(userRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping ("/{username}")
    public User getUser (@PathVariable String username)
    {
        return userService.getUser(username);
    }
}
