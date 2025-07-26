package com.sunbeam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.entities.Cart;
import com.sunbeam.entities.OrderItem;
import com.sunbeam.services.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired 
    private CartService service;

    @GetMapping("/{userId}") public Cart get(@PathVariable int userId) { return service.getCartByUserId(userId); }
    @DeleteMapping("/{userId}/items/{productId}") public void remove(@PathVariable int userId, @PathVariable int productId) { service.removeFromCart(userId, productId); }
}
