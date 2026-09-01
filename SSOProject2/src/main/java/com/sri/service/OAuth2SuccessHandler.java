package com.sri.service;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    public OAuth2SuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException, ServletException {

        log.info("OAuth2 authentication successful");

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2User oauthUser = oauthToken.getPrincipal();

        String provider = oauthToken.getAuthorizedClientRegistrationId();

        log.info("OAuth2 provider: {}", provider);

        String email = oauthUser.getAttribute("email");

        // Azure fallback
        if (email == null && provider.equals("azure")) {

            log.debug("Email not found in standard attribute. Using Azure preferred_username");

            email = oauthUser.getAttribute("preferred_username");
        }

        log.debug("Authenticated user email: {}", email);

        if (userService.getUserByEmail(email) != null) {

            log.info("Existing user found. Redirecting to dashboard");

            response.sendRedirect("http://localhost:5501/dashboard.html");

        } else {

            log.info("New user detected. Redirecting to registration page");

            response.sendRedirect("http://localhost:5501/register.html");
        }
    }
}