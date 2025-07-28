package com.sunbeam.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.entities.Cart;
import com.sunbeam.entities.CartItem;
import com.sunbeam.entities.Product;
import com.sunbeam.entities.User;
import com.sunbeam.request.AddItemRequest;
import com.sunbeam.response.ApiResponse;
import com.sunbeam.services.CartItemService;
import com.sunbeam.services.CartService;
import com.sunbeam.services.UserService;
import com.sunbeam.services.UserService1;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

	private final CartService cartService;
	private final CartItemService cartItemService;
	private final UserService1 userService;
//	private final ProductService productService; 
	
	@GetMapping
	public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt)throws Exception{
		User user = userService.findUserProfileByJwt(jwt);
		Cart cart = cartService.findUserCart(user);
//		System.out.println("Cart - " + cart.getUser().getEmail());		
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);	
	}
	
	
//	@PutMapping("/add")
//	public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req, 
//												  @RequestHeader("Authorization") String jwt)
//												  throws Exception{  //Add ProductException
//		
//		User user = userService.findUserByJwtToken(jwt);
//		Product product = productService.findProductById(req.getProductId());
//		
//		CartItem item = cartService.addCartItem(user, 
//				product, 
//				req.getSize(), 
//				req.getQuantity());
//		ApiResponse res = new ApiResponse();
//		res.setMessage("Item Added To Cart Successfully...");
//	
//		return new ResponseEntity<CartItem>(item, HttpStatus.ACCEPTED);
//	}

	
	@DeleteMapping("/item/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItemhandler(
						@PathVariable Long cartItemId,
						@RequestHeader("Authorization") String jwt
						)throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);
		
		ApiResponse res = new ApiResponse();
		res.setMessage("Item remove From Cart");
		
		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);	
	}
	
	
	@PutMapping("/item/{cartItemid}")
	public ResponseEntity<CartItem> updateCartItemHandler(
									@PathVariable Long cartItemId,
									@RequestBody CartItem cartItem,
									@RequestHeader("Authentication")String jwt)
								throws Exception{
				
		User user = userService.findUserByJwtToken(jwt);
		
		CartItem updateCartItem = null;
		if(cartItem.getQuantity() > 0) {
			updateCartItem = cartItemService.updateCartItem(user.getId(), 
					cartItemId, cartItem);
		}
		return new ResponseEntity<CartItem>(updateCartItem, HttpStatus.ACCEPTED);
	}
}
