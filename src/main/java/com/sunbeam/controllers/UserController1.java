package com.sunbeam.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.entities.User;
import com.sunbeam.response.AuthResponse;
import com.sunbeam.response.SignupRequest;
import com.sunbeam.services.UserService;
import com.sunbeam.services.UserService1;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController1 {
	
	private final UserService1 userService;
	
	@GetMapping("/users/profile")
	public ResponseEntity<User> createUserHandler(
			@RequestHeader("Authorization") String jwt
			) throws Exception { 
		
		User user = userService.findUserByJwtToken(jwt);
		
		return ResponseEntity.ok(user);
	}

}
