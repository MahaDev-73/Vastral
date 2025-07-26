package com.sunbeam.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sunbeam.entities.Credentials;
import com.sunbeam.entities.User;
import com.sunbeam.response.UserResponse;

public interface UserService  extends UserDetailsService{
	User getCustomerByEmail(String email);

	User getCustomerByCredentials(Credentials cr);

	UserResponse<String> save(User users);
	
	User loadUserByUsername(String email);

}

