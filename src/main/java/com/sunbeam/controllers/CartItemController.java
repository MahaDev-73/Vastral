package com.sunbeam.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sunbeam.services.CartItemService;
import com.sunbeam.services.UserService1;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

	private CartItemService cartItemService;
	private UserService1 userService;
	
	public CartItemController(CartItemService cartItemService, UserService1 userService) {
		this.cartItemService=cartItemService;
		this.userService=userService;
	}
	

}
