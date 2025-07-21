package com.sunbeam.controller;
//package com.sunbeam.restcontroller;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import com.sunbeam.models.CustomerResponse;
//
//@ControllerAdvice
//public class ExceptionHandler {
//	
//	//@ExceptionHandler(Exception.class);
//	@ExceptionHandler
//	public CustomerResponse<?> handleException(Exception ex) {
//		ex.printStackTrace();
//		String message = ex.getClass().getName() + ": " + ex.getMessage();
//		return CustomerResponse.error(message);
//	}
//}
