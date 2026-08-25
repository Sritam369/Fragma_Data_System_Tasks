package com.sri.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sri.entity.UserDetails;
import com.sri.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService service;
	  
	@GetMapping("/")
    public String home() {
        return "home";
    }
	
	@GetMapping("/profile")
	public String profile(@AuthenticationPrincipal OAuth2User oAuth2User,OAuth2AuthenticationToken authentication,Map<String, Object> map) {

	    String provider = authentication.getAuthorizedClientRegistrationId();

	    String email;
	    String picture;
	    String name;

	    if (provider.equals("google")) {

	        name = oAuth2User.getAttribute("name");
	        email = oAuth2User.getAttribute("email");
	        picture = oAuth2User.getAttribute("picture");

	    } else {

	        name = oAuth2User.getAttribute("name");
	        email = oAuth2User.getAttribute("email");
	        picture = oAuth2User.getAttribute("avatar_url");

	    }

	    UserDetails user = service.getUserByEmail(email);

	    if (user != null) {

	        map.put("user", user);
	        map.put("picture", picture);

	        return "profile";

	    } else {

	        UserDetails newUser = new UserDetails();

	        newUser.setName(name);
	        newUser.setEmail(email);

	        map.put("user", newUser);

	        return "register";
	    }
	}
	
	@PostMapping("/register")
	public String register(UserDetails user) {

	    service.addUser(user);

	    return "redirect:/profile";
	}
}
