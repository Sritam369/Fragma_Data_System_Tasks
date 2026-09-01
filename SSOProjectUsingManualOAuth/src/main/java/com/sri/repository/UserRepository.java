package com.sri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sri.entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {

	@Query("select u from UserDetails u where u.email=?1")
	UserDetails fetchUserByMail(String mail);
}
