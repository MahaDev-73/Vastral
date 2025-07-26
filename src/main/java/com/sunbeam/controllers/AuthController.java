package com.sunbeam.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.entities.User;
import com.sunbeam.response.SignupRequest;

@RestController
public class AuthController {
	
	@PostMapping
	public ResponseEntity<User> createUserhandler(@RequestBody SignupRequest req){
		
		User user = new User();
		user.setEmail(req.getEmail());
		user.setFullName(req.getFullName());
		
		return ResponseEntity.ok(user);
	}
}
