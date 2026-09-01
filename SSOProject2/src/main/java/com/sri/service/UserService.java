package com.sri.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sri.entity.UserDetails;
import com.sri.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CacheEvict(value = "users", key = "#user.email")
    public UserDetails addUser(UserDetails user) {

        log.info("Adding new user with email: {}", user.getEmail());

        UserDetails savedUser = userRepository.save(user);

        log.info("User added successfully with email: {}", savedUser.getEmail());

        return savedUser;
    }


    @Cacheable(value = "users", key = "#email")
    public UserDetails getUserByEmail(String email) {

        log.debug("Searching for user with email: {}", email);

        UserDetails user = userRepository.findByEmail(email);

        if (user != null) {
            log.debug("User found with email: {}", email);
        } else {
            log.debug("No user found with email: {}", email);
        }

        return user;
    }
}