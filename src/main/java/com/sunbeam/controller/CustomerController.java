
package com.sunbeam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.model.Customer;
import com.sunbeam.response.Credentials;
import com.sunbeam.response.CustomerResponse;
import com.sunbeam.security.JwtUtil; //import com.sunbeam.security.JwtUtil;
import com.sunbeam.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService userService;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	public CustomerController(CustomerService userService) {
		this.userService = userService;
	}

	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Customer customer) {
		System.out.println(customer);
		CustomerResponse<String> register = userService.save(customer);
		return ResponseEntity.ok(register);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Credentials cred) {
		System.out.println(cred);
		CustomerResponse<String> resp = userService.authenticate(cred);
		return ResponseEntity.ok(resp);
		
	} 
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody Credentials cr) {
		// authenticate user with authentication manager		
		Authentication auth = new UsernamePasswordAuthenticationToken(cr.getEmail(), cr.getPassword());
		System.out.println("BEFORE AUTH: " + auth);
		auth = authManager.authenticate(auth);
		System.out.println("AFTER AUTH: " + auth);
		// after authentication, create JWT token and return.
		String token = jwtUtil.createToken(auth);
		return ResponseEntity.ok(token);
	}
}

 