package com.sri.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sri.dto.RegisterRequest;
import com.sri.entity.UserDetails;
import com.sri.exception.UserAlreadyRegisteredException;
import com.sri.exception.UserNotAuthenticatedException;
import com.sri.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;


    @GetMapping("/api/profile")
    public ResponseEntity<?> profile(Authentication authentication) {

        log.info("Profile request received");

        if (authentication == null || !authentication.isAuthenticated()) {

            log.warn("Profile request rejected: user is not authenticated");

            throw new UserNotAuthenticatedException("User is not authenticated");
        }


        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2User oauthUser = oauthToken.getPrincipal();


        String provider = oauthToken.getAuthorizedClientRegistrationId();

        String name = oauthUser.getAttribute("name");

        String email = oauthUser.getAttribute("email");

        String picture = oauthUser.getAttribute("picture");


        // Azure fallback
        if (email == null && provider.equals("azure")) {

            log.debug("Email not found in standard attribute. Using Azure preferred_username");

            email = oauthUser.getAttribute("preferred_username");
        }


        if (email == null) {

            log.warn("User email is not available");

            throw new UserNotAuthenticatedException("User email is not available");
        }


        UserDetails user = service.getUserByEmail(email);


        // User is already registered
        if (user != null) {

            log.info("Registered user profile retrieved");

            Map<String, Object> response =
                    Map.of(
                            "registered", true,

                            "provider", provider == null ? "" : provider,

                            "name", user.getName() == null ? "" : user.getName(),

                            "email", user.getEmail() == null ? "" : user.getEmail(),

                            "phone", user.getPhone() == null ? "" : user.getPhone(),

                            "department", user.getDepartment() == null ? "" : user.getDepartment(),

                            "designation", user.getDesignation() == null ? "" : user.getDesignation(),

                            "picture", picture == null ? "" : picture
                    );

            return ResponseEntity.ok(response);
        }


        // User is authenticated but not registered
        log.info("Authenticated user is not registered");

        Map<String, Object> response =
                Map.of(
                        "registered", false,

                        "provider", provider == null ? "" : provider,

                        "name", name == null ? "" : name,

                        "email", email,

                        "picture", picture == null ? "" : picture
                );


        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }


    @PostMapping("/api/register")
    public ResponseEntity<?> register(Authentication authentication,@RequestBody RegisterRequest request) {

        log.info("User registration request received");


        if (authentication == null || !authentication.isAuthenticated()) {

            log.warn("Registration rejected: user is not authenticated");

            throw new UserNotAuthenticatedException("User is not authenticated");
        }


        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2User oauthUser = oauthToken.getPrincipal();


        String provider =  oauthToken.getAuthorizedClientRegistrationId();

        String email = oauthUser.getAttribute("email");

        String name = oauthUser.getAttribute("name");


        // Azure fallback
        if (email == null && provider.equals("azure")) {

            log.debug("Email not found in standard attribute. Using Azure preferred_username");

            email = oauthUser.getAttribute("preferred_username");
        }


        if (email == null) {

            log.warn("Registration failed: user email is not available");

            throw new UserNotAuthenticatedException("User email is not available");
        }


        UserDetails existing = service.getUserByEmail(email);


        if (existing != null) {

            log.warn("Registration rejected: user is already registered");

            throw new UserAlreadyRegisteredException("User already registered");
        }


        UserDetails user = new UserDetails();

        user.setName(name);
        user.setEmail(email);
        user.setPhone(request.getPhone());
        user.setDepartment(request.getDepartment());
        user.setDesignation(request.getDesignation());


        UserDetails savedUser = service.addUser(user);


        log.info("User registration completed successfully");


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }
}