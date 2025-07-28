package com.sunbeam.services;

import com.sunbeam.entities.User;

public interface UserService1 {

	 User findUserByJwtToken(String jwt)throws Exception;
	 User findUserByEmail(String email)throws Exception;
	 User findUserProfileByJwt(String jwt) throws Exception;
	
}
