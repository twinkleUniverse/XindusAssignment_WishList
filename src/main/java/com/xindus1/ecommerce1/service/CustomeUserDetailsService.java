package com.xindus1.ecommerce1.service;

import com.xindus1.ecommerce1.model.User;
import com.xindus1.ecommerce1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Load user from database
        User user=userRepository.findByEmail(username).orElseThrow(()->new RuntimeException("User not found..!"));

        return user;
    }
}
