package com.sri.service;

import org.springframework.stereotype.Service;

import com.sri.entity.UserDetails;
import com.sri.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserDetails addUser(UserDetails user) {
        return userRepository.save(user);
    }

    public UserDetails getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }
}