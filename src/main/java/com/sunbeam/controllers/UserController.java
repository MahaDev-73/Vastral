//package com.sunbeam.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sunbeam.entities.Credentials;
//import com.sunbeam.entities.User;
//import com.sunbeam.response.UserResponse;
//import com.sunbeam.security.JwtUtil;
//import com.sunbeam.services.UserService;
//
//@RestController
//	@RequestMapping
//	
//	public class UserController {
//		@Autowired
//		//@Lazy
//		private UserService userService;
//		@Autowired
//		private JwtUtil jwtUtil;
//		@Autowired
//		private AuthenticationManager authManager;
//		@Autowired
//		public UserController(UserService userService) {
//			this.userService = userService;
//		}
//		@Autowired
//		PasswordEncoder pwen;
//		
//		@PostMapping("/register")
//		public ResponseEntity<UserResponse<String>> register(@RequestBody User users) {
//		    System.out.println("Registering user: " + users.getEmail()); // Optional debug log
//
//		    UserResponse<String> response = userService.save(users);
//
//		    return ResponseEntity.ok(response);
//		}
//		
//		@PostMapping("/authenticate")  
//		public ResponseEntity<?> authenticate(@RequestBody Credentials cr) {
//			// authenticate user with authentication manager		
//			Authentication auth = new UsernamePasswordAuthenticationToken(cr.getEmail(), cr.getPassword());
//			System.out.println("BEFORE AUTH: " + auth);
//			auth = authManager.authenticate(auth);
//			System.out.println("AFTER AUTH: " + auth);
//			// after authentication, create JWT token and return.
//			String token = jwtUtil.createToken(auth);
//			return ResponseEntity.ok(token);
//		}
//
//
//
//}
