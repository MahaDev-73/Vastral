 package com.sunbeam.response;


import com.sunbeam.models.UserRole;

import lombok.Data;

@Data
public class AuthResponse {
	
	private String jwt;
	private String message;
	private UserRole role;

}
