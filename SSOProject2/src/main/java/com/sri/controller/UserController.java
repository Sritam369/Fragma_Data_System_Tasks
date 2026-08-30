package com.sri.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sri.dto.RegisterRequest;
import com.sri.entity.UserDetails;
import com.sri.exception.UserAlreadyRegisteredException;
import com.sri.exception.UserNotAuthenticatedException;
import com.sri.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;


    @GetMapping("/api/profile")
    public ResponseEntity<?> profile(HttpSession session) {

        String provider =
                (String) session.getAttribute("oauthProvider");

        String name =
                (String) session.getAttribute("oauthName");

        String email =
                (String) session.getAttribute("oauthEmail");

        String picture =
                (String) session.getAttribute("oauthPicture");


        // No OAuth user in session
        if (email == null) {
            throw new UserNotAuthenticatedException();
        }


        // Find user in database
        UserDetails user =
                service.getUserByEmail(email);


        // User already registered
        if (user != null) {

            Map<String, Object> response = Map.of(
                    "registered", true,
                    "provider", provider == null ? "" : provider,
                    "name", user.getName() == null
                            ? "" : user.getName(),
                    "email", user.getEmail() == null
                            ? "" : user.getEmail(),
                    "phone", user.getPhone() == null
                            ? "" : user.getPhone(),
                    "department", user.getDepartment() == null
                            ? "" : user.getDepartment(),
                    "designation", user.getDesignation() == null
                            ? "" : user.getDesignation(),
                    "picture", picture == null
                            ? "" : picture
            );

            return ResponseEntity.ok(response);
        }


        // Authenticated but not registered
        Map<String, Object> response = Map.of(
                "registered", false,
                "provider", provider == null ? "" : provider,
                "name", name == null ? "" : name,
                "email", email == null ? "" : email,
                "picture", picture == null ? "" : picture
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }


    @PostMapping("/api/register")
    public ResponseEntity<?> register(
            HttpSession session,
            @RequestBody RegisterRequest request) {


        String email =
                (String) session.getAttribute("oauthEmail");

        String name =
                (String) session.getAttribute("oauthName");


        // OAuth session doesn't exist
        if (email == null) {
            throw new UserNotAuthenticatedException();
        }


        // Check whether user already exists
        UserDetails existing =
                service.getUserByEmail(email);

        if (existing != null) {

            throw new UserAlreadyRegisteredException(
                    "User already registered"
            );
        }


        // Create new user
        UserDetails user =
                new UserDetails();

        user.setName(name);
        user.setEmail(email);

        user.setPhone(
                request.getPhone()
        );

        user.setDepartment(
                request.getDepartment()
        );

        user.setDesignation(
                request.getDesignation()
        );


        UserDetails savedUser =
                service.addUser(user);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }
}