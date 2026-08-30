package com.sri.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public List<Map<String, String>> getProviders() {

        return List.of(
            Map.of(
                "id", "google",
                "name", "Google",
                "loginUrl", "/api/auth/login/google"
            ),
            Map.of(
                "id", "aws",
                "name", "Amazon",
                "loginUrl", "/api/auth/login/aws"
            ),
            Map.of(
                "id", "azure",
                "name", "Microsoft",
                "loginUrl", "/api/auth/login/azure"
            )
        );
    }
    
    public Map<String, String> getLoginUrl(String provider) {

        if (!Set.of("google", "aws", "azure").contains(provider)) {

            throw new IllegalArgumentException(
                    "Unsupported authentication provider: " + provider
            );
        }

        return Map.of(
                "provider", provider,
                "loginUrl", "/oauth2/authorization/" + provider
        );
    }
}