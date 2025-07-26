package com.sunbeam.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.response.ApiResponse;

@RestController
public class HomeController {
	
	@GetMapping
	public ApiResponse HomeControllerHandler() {
		ApiResponse response = new ApiResponse();
		response.setMessage("Welcome to ecommerce multi vendor");
		return response;
	}
}
