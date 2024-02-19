package com.xindus1.ecommerce1.service;

import com.xindus1.ecommerce1.dto.requestdto.UserRequestDTO;
import com.xindus1.ecommerce1.dto.responsedto.UserResponseDTO;
import com.xindus1.ecommerce1.exception.UserNotFoundException;

public interface UserService {
    public String addUser(UserRequestDTO userRequestDTO);
    public UserResponseDTO getUser(String username) throws UserNotFoundException;

}
