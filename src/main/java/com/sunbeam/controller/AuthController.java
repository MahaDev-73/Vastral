package com.sunbeam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.model.User;
import com.sunbeam.response.SignupRequest;


@RestController
public class AuthController {
	
	public ResponseEntity<User> createUserHandler(@RequestBody SignupRequest req){
		
		User user = new User();
		user.setEmail(req.getEmail());
		user.setFullName(req.getFullName());
		return ResponseEntity.ok(user);
	}
}
