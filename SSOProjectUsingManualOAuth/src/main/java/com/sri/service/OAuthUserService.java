package com.sri.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OAuthUserService {

    private final RestClient restClient = RestClient.builder().build();

    // Google user

    public GoogleUser getGoogleUser(String accessToken) {

        return restClient.get().uri("https://openidconnect.googleapis.com/v1/userinfo")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .body(GoogleUser.class);
    }

    // Github user

    public GithubUser getGithubUser(String accessToken) {

        return restClient.get().uri("https://api.github.com/user")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .body(GithubUser.class);
    }

    // Google user details

    public static class GoogleUser {

        private String name;
        private String email;
        private String picture;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }

    // Github user details

    public static class GithubUser {

        private String name;
        private String email;
        private String avatar_url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }
    
}