package com.sri.service;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sri.dto.EmailProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final EmailProvider emailProvider;

    public OAuth2SuccessHandler(
            OAuth2AuthorizedClientService authorizedClientService,
            EmailProvider emailProvider) {

        this.authorizedClientService = authorizedClientService;
        this.emailProvider = emailProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken oauth2Authentication = (OAuth2AuthenticationToken) authentication;

        String provider =  oauth2Authentication.getAuthorizedClientRegistrationId();

        if (provider == null || provider.isBlank()) {
            throw new IllegalStateException(
                    "OAuth2 provider is missing");
        }

        String email = oauth2Authentication.getPrincipal().getAttribute("email");
        
        if (provider.equals("azure")) {
            email = oauth2Authentication.getPrincipal().getAttribute("preferred_username");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalStateException(
                    "Email not found in OAuth2 user attributes for provider: "
                            + provider);
        }
        
        emailProvider.setEmail(email);
        emailProvider.setProvider(provider);

    }
}