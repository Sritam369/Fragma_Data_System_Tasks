package com.sri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sri.entity.UserDetails;
import com.sri.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public UserDetails addUser(UserDetails user) {
		return repo.save(user);
	}
	
	public UserDetails getUserByEmail(String email) {
		
        UserDetails user = repo.fetchUserByMail(email);
        return user;
    }

}
