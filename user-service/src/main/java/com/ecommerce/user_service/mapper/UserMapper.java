package com.ecommerce.user_service.mapper;

import com.ecommerce.user_service.dto.UserRequestDTO;
import com.ecommerce.user_service.dto.UserResponseDTO;
import com.ecommerce.user_service.entity.User;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface UserMapper {


    User toEntity (UserRequestDTO userRequestDTO);

    UserResponseDTO toResponseDTO (User user);




}
