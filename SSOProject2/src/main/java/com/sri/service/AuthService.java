package com.sri.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sri.entity.OAuthProvider;
import com.sri.repository.OAuthProviderRepository;

@Service
public class AuthService {

	@Autowired
	private OAuthProviderRepository oauthProviderRepository;
	
    	public List<Map<String, String>> getProviders() {

    	    return oauthProviderRepository.findAll()
    	        .stream()
    	        .map(provider -> Map.of(
    	            "name", provider.getProviderName(),
    	            "id", provider.getProviderId()
    	        ))
    	        .toList();
    	
    }
       	 
}