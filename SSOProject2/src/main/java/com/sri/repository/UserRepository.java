package com.sri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sri.entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {

	UserDetails findByEmail(String email);
}
