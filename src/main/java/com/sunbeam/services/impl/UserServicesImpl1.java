package com.sunbeam.services.impl;

import org.springframework.stereotype.Service;

import com.sunbeam.config.JwtProvider;
import com.sunbeam.daos.UserRepository;
import com.sunbeam.entities.User;
import com.sunbeam.services.UserService;
import com.sunbeam.services.UserService1;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServicesImpl1 implements UserService1 {

	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new Exception("User not found with email - " + email);
		}
		return user;
	}

	@Override
	public User findUserProfileByJwt(String token) throws Exception {
	    String email = jwtProvider.getEmailFromJwtToken(token);
	    User user = userRepository.findByEmail(email);
	    if (user == null) {
	        throw new Exception("User not found with email - " + email);
	    }
	    return user;
	}

}
