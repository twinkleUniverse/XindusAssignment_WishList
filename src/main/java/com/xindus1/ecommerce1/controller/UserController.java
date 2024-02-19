package com.xindus1.ecommerce1.controller;

import com.xindus1.ecommerce1.dto.requestdto.AuthRequest;
import com.xindus1.ecommerce1.dto.requestdto.UserRequestDTO;
import com.xindus1.ecommerce1.dto.responsedto.AuthResponse;
import com.xindus1.ecommerce1.dto.responsedto.UserResponseDTO;
import com.xindus1.ecommerce1.exception.UserNotFoundException;
import com.xindus1.ecommerce1.security.JwtService;
import com.xindus1.ecommerce1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("User")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtService helper;
    @Autowired
    private AuthenticationManager manager;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity addUser(@RequestBody UserRequestDTO userRequestDTO) {
        String message = userService.addUser(userRequestDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest request) {

        String str = this.doAuthenticate(request.getEmail(), request.getPassword());

        if (!str.equals("successful")) return new ResponseEntity<>(str, HttpStatus.CONFLICT);
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        System.out.println(userDetails);
        String token = this.helper.generateToken(userDetails);

        AuthResponse response = AuthResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            Authentication mng = manager.authenticate(authentication);
            return "successful";

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @GetMapping("/getUser")
    public ResponseEntity getUserByName(@RequestParam("username") String username) throws UserNotFoundException {
        try {
            UserResponseDTO userResponseDTO = userService.getUser(username);
            return new ResponseEntity(userResponseDTO, HttpStatus.FOUND);
        } catch (UserNotFoundException u) {
            return new ResponseEntity(u.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
