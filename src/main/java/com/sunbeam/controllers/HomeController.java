package com.sunbeam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.response.ApiResponse;
import com.sunbeam.services.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeController {
	@Autowired
	 private  HomeService homeService;

	    @GetMapping
	    public ResponseEntity<ApiResponse> home(){
	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setMessage("Ecommerce multi vendor system");
	        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
	    }

}
