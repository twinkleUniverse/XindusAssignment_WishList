package com.xindus1.ecommerce1.service.impl;

import com.xindus1.ecommerce1.dto.requestdto.UserRequestDTO;
import com.xindus1.ecommerce1.dto.responsedto.UserResponseDTO;
import com.xindus1.ecommerce1.exception.UserNotFoundException;
import com.xindus1.ecommerce1.model.User;
import com.xindus1.ecommerce1.repository.UserRepository;
import com.xindus1.ecommerce1.service.UserService;
import com.xindus1.ecommerce1.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    final UserRepository userRepository;
    @Autowired
    final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public String addUser(UserRequestDTO userRequestDTO){
        User user= UserTransformer.userRequestToUser(userRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User "+userRequestDTO.getUsername()+" added successfully";
    }

    public UserResponseDTO getUser(String username) throws UserNotFoundException {
        User user=userRepository.findByUsername(username);

        if(user==null){
            throw  new UserNotFoundException("User not found");
        }
        UserResponseDTO userResponseDTO= UserTransformer.userToResponseDTO(user);
        return userResponseDTO;
    }


}
