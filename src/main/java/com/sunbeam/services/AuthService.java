package com.sunbeam.services;


import com.sunbeam.models.UserRole;
import com.sunbeam.request.LoginRequest;
import com.sunbeam.response.AuthResponse;
import com.sunbeam.response.SignupRequest;

public interface AuthService {
	
//	Send otp method
	
	void sentLoginOtp(String email, UserRole role) throws Exception;
	
	String createUser(SignupRequest req) throws Exception;
	
	AuthResponse signing(LoginRequest req) throws Exception;
	
}
