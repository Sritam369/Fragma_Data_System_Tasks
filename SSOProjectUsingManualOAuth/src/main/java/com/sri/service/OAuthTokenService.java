package com.sri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.sri.config.OAuthConfig;

@Service
public class OAuthTokenService {

    private final RestClient restClient;

    @Autowired
    private OAuthConfig config;

    public OAuthTokenService() {
        this.restClient = RestClient.builder().build();
    }

    // Google token

    public GoogleTokenResponse getGoogleToken(String code) {

        return restClient.post().uri("https://oauth2.googleapis.com/token").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(
                        "code=" + code
                        + "&client_id=" + config.getGoogleClientId()
                        + "&client_secret=" + config.getGoogleClientSecret()
                        + "&redirect_uri=http://localhost:4041/login/oauth2/code/google"
                        + "&grant_type=authorization_code"
                )
                .retrieve()
                .body(GoogleTokenResponse.class);
    }

    // Github token

    public GithubTokenResponse getGithubToken(String code) {

        return restClient.post()
                .uri("https://github.com/login/oauth/access_token").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Accept", "application/json")
                .body(
                        "code=" + code
                        + "&client_id=" + config.getGithubClientId()
                        + "&client_secret=" + config.getGithubClientSecret()
                        + "&redirect_uri=http://localhost:4041/login/oauth2/code/github"
                )
                .retrieve()
                .body(GithubTokenResponse.class);
    }

    // Google token response

    public static class GoogleTokenResponse {

        private String access_token;
        private String id_token;
        private String token_type;
        private String scope;
        private Integer expires_in;

        public String getAccessToken() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getIdToken() {
            return id_token;
        }

        public void setId_token(String id_token) {
            this.id_token = id_token;
        }

        public String getTokenType() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public Integer getExpiresIn() {
            return expires_in;
        }

        public void setExpires_in(Integer expires_in) {
            this.expires_in = expires_in;
        }
    }

    // Github token response

    public static class GithubTokenResponse {

        private String access_token;
        private String token_type;
        private String scope;

        public String getAccessToken() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getTokenType() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }
    }
}