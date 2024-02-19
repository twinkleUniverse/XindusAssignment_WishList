package com.xindus1.ecommerce1.transformer;

import com.xindus1.ecommerce1.dto.requestdto.UserRequestDTO;
import com.xindus1.ecommerce1.dto.responsedto.UserResponseDTO;
import com.xindus1.ecommerce1.model.User;

public class UserTransformer {

    public static User userRequestToUser(UserRequestDTO userRequestDTO){
        return User.builder()
                .username(userRequestDTO.getUsername())
                .address(userRequestDTO.getAddress())
                .email(userRequestDTO.getEmail())
                .mob(userRequestDTO.getMob())
                .password(userRequestDTO.getPassword())
                .build();
    }

    public static UserResponseDTO userToResponseDTO(User user){
        return UserResponseDTO.builder()
                .username(user.getUsername())
                .address(user.getAddress())
                .email(user.getEmail())
                .mob(user.getMob())
                .build();
    }
}
