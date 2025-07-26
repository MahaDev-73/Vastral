package com.sunbeam.services.impl;

import org.springframework.stereotype.Service;

import com.sunbeam.entities.Cart;
import com.sunbeam.entities.CartItem;
import com.sunbeam.services.CartService;

@Service
public class CartServiceImpl implements CartService {
    @Override
    public Cart getCartByUserId(int userId) {
        // Implementation logic
        return null;
    }

    @Override
    public Cart addToCart(int userId, CartItem item) {
        // Implementation logic
        return null;
    }

    @Override
    public void removeFromCart(int userId, int productId) {
        // Implementation logic
    }
}
