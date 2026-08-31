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
import jakarta.servlet.http.HttpSession;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    public OAuth2SuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2User oauthUser = oauthToken.getPrincipal();

        String provider = oauthToken.getAuthorizedClientRegistrationId();

        String name = oauthUser.getAttribute("name");

        String email = oauthUser.getAttribute("email");

        String picture = oauthUser.getAttribute("picture");

        // Azure fallback
        if (email == null && "azure".equals(provider)) {
            email = oauthUser.getAttribute("preferred_username");
        }

        HttpSession session = request.getSession();

        session.setAttribute("oauthName", name);
        session.setAttribute("oauthEmail", email);
        session.setAttribute("oauthProvider", provider);
        session.setAttribute("oauthPicture", picture);

        if (userService.getUserByEmail(email) != null) {

            response.sendRedirect(
                "http://localhost:5501/dashboard.html"
            );

        } else {

            response.sendRedirect(
                "http://localhost:5501/register.html"
            );
        }
    }
}