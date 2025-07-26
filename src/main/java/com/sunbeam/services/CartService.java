package com.sunbeam.services;



import org.springframework.stereotype.Service;

import com.sunbeam.entities.Cart;
import com.sunbeam.entities.CartItem;

public interface CartService {
    Cart getCartByUserId(int userId);
    Cart addToCart(int userId, CartItem item);
    void removeFromCart(int userId, int productId);
}
