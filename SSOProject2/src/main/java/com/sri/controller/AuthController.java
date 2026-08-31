package com.sri.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sri.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping("/providers")
    public ResponseEntity<?> getProviders() {

        return ResponseEntity.ok(
            Map.of(
                "providers",authService.getProviders()
            )
        );
    }


    @GetMapping("/login/{provider}")
    public void login(@PathVariable String provider,HttpServletResponse response) throws IOException {

        String loginUrl = authService.getLoginUrl(provider).get("loginUrl");

        response.sendRedirect(loginUrl);
    }
}