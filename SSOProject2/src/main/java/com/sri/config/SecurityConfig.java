package com.sri.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.sri.service.OAuth2SuccessHandler;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2SuccessHandler oauth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .cors(cors -> {})

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/api/auth/providers",
                    "/api/auth/login/**",
                    "/oauth2/**",
                    "/login/**"
                ).permitAll()

                .anyRequest().authenticated()
            )

            .oauth2Login(oauth -> oauth
                .loginPage("http://localhost:5051/")
                .successHandler(oauth2SuccessHandler)
            )

            .logout(logout -> logout
                .logoutUrl("/api/logout")

                .logoutSuccessHandler(
                    (request, response, authentication) -> {
                        response.setStatus(
                            HttpServletResponse.SC_OK
                        );
                    }
                )

                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
            );

        return http.build();
    }
}