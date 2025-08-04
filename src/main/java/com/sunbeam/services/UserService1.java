package com.sunbeam.services;

import org.springframework.stereotype.Service;

import com.sunbeam.entities.User;

@Service
public interface UserService1 {

	 User findUserByEmail(String email)throws Exception;
	 User findUserProfileByJwt(String jwt) throws Exception;
	
}
