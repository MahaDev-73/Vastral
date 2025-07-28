package com.sunbeam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.daos.UserRepository;
import com.sunbeam.entities.User;
import com.sunbeam.entities.VerificationCode;

import com.sunbeam.models.UserRole;
import com.sunbeam.request.LoginOtpRequest;
import com.sunbeam.request.LoginRequest;
import com.sunbeam.response.ApiResponse;
import com.sunbeam.response.AuthResponse;
import com.sunbeam.response.SignupRequest;
import com.sunbeam.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController // act as a controller class
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserRepository userRepository;
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {
		String jwt = authService.createUser(req);
		AuthResponse res = new AuthResponse();
		res.setJwt(jwt);
		res.setMessage("register success");
		res.setRole(UserRole.ROLE_CUSTOMER);
		return ResponseEntity.ok(res);
	}

	@PostMapping("/sent/login-signup-otp")
	public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
		authService.sentLoginOtp(req.getEmail(), req.getRole());
		ApiResponse res = new ApiResponse();
		res.setMessage("OTP sent successfully");
		return ResponseEntity.ok(res);
	}

	@PostMapping("/signing")
	public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {

		AuthResponse authResponse = authService.signing(req);

		return ResponseEntity.ok(authResponse);
	}
}
