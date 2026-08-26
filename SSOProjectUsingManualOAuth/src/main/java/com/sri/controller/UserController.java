package com.sri.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.sri.config.OAuthConfig;
import com.sri.entity.UserDetails;
import com.sri.model.OAuthUser;
import com.sri.service.OAuthTokenService;
import com.sri.service.OAuthUserService;
import com.sri.service.UserService;


import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private OAuthConfig config;

    @Autowired
    private OAuthTokenService tokenService;

    @Autowired
    private OAuthUserService oauthUserService;

    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    // Creating Authorization url
    
    @GetMapping("/oauth2/authorize/{provider}")
    public RedirectView authorize(@PathVariable String provider,HttpSession session) {

        // Generate state
        String state = UUID.randomUUID().toString();

        // Store state and provider in session
        session.setAttribute("OAUTH_STATE", state);
        session.setAttribute("OAUTH_PROVIDER", provider);

        String url;

        if (provider.equals("google")) {

            url = "https://accounts.google.com/o/oauth2/v2/auth"
                    + "?client_id=" + config.getGoogleClientId()
                    + "&redirect_uri=http://localhost:4041/login/oauth2/code/google"
                    + "&response_type=code"
                    + "&scope=openid%20email%20profile"
                    + "&state=" + state
                    + "&prompt=select_account";

        } else if (provider.equals("github")) {

            url = "https://github.com/login/oauth/authorize"
                    + "?client_id=" + config.getGithubClientId()
                    + "&redirect_uri=http://localhost:4041/login/oauth2/code/github"
                    + "&scope=read%3Auser%20user%3Aemail"
                    + "&state=" + state
                    + "&prompt=select_account";

        } else {

            return new RedirectView("/");
        }


        // Redirect browser to provider
        return new RedirectView(url);
    }

    
    // Callback endpoint
    
    @GetMapping("/login/oauth2/code/{provider}")
    public String callback(@PathVariable String provider,String code,String state,HttpSession session) {

        String savedState = (String) session.getAttribute("OAUTH_STATE");

        String savedProvider = (String) session.getAttribute("OAUTH_PROVIDER");

        if (savedState == null || !savedState.equals(state)) {
            return "redirect:/?error=invalid_state";
        }

        if (savedProvider == null || !savedProvider.equals(provider)) {
            return "redirect:/?error=invalid_provider";
        }

        OAuthUser oauthUser;

        if (provider.equals("google")) {

            OAuthTokenService.GoogleTokenResponse tokenResponse = tokenService.getGoogleToken(code);

            String accessToken = tokenResponse.getAccessToken();
            
            String idToken = tokenResponse.getIdToken();
            
            OAuthUserService.GoogleUser googleUser = oauthUserService.getGoogleUser(accessToken);

            oauthUser = new OAuthUser("google",googleUser.getName(),googleUser.getEmail(),googleUser.getPicture());

        } else if (provider.equals("github")) {

            OAuthTokenService.GithubTokenResponse tokenResponse = tokenService.getGithubToken(code);

            String accessToken = tokenResponse.getAccessToken();

            OAuthUserService.GithubUser githubUser = oauthUserService.getGithubUser(accessToken);

            String email = githubUser.getEmail();

            oauthUser = new OAuthUser("github",githubUser.getName(),email,githubUser.getAvatar_url());

        } else {

            return "redirect:/?error=unknown_provider";
        }

        // Saving the object in session scope
        session.setAttribute("LOGGED_IN_USER", oauthUser);

        // OAuth request information is no longer needed
        session.removeAttribute("OAUTH_STATE");
        session.removeAttribute("OAUTH_PROVIDER");

        return "redirect:/profile";
    }
    
    @GetMapping("/profile")
    public String profile(HttpSession session,Model model) {

        OAuthUser oauthUser = (OAuthUser) session.getAttribute("LOGGED_IN_USER");

        if (oauthUser == null) {
            return "redirect:/";
        }

        UserDetails user = service.getUserByEmail(oauthUser.getEmail());

        if (user != null) {

            model.addAttribute("user", user);
            model.addAttribute("picture", oauthUser.getPicture());

            return "profile";
        }

        UserDetails newUser = new UserDetails();

        newUser.setName(oauthUser.getName());
        newUser.setEmail(oauthUser.getEmail());

        model.addAttribute("user", newUser);

        return "register";
    }
    
    @PostMapping("/register")
    public String register(UserDetails user) {

        service.addUser(user);

        return "redirect:/profile";
    }
    
    @PostMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }

    
}