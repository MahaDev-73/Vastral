package com.sunbeam.services;

import com.sunbeam.entities.Cart;
import com.sunbeam.entities.CartItem;
import com.sunbeam.entities.Product;
import com.sunbeam.entities.User;

public interface CartService {

	public CartItem addCartItem(	
			User user,
			Product product,
			String size,
			int quantity
		);
	
	public Cart findUserCart(User user);
	
	
}
