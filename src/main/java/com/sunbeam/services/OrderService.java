package com.sunbeam.services;

import java.util.List;
import java.util.Set;

import com.sunbeam.entities.Address;
import com.sunbeam.entities.Cart;
import com.sunbeam.entities.User;
import com.sunbeam.exceptions.OrderException;
import com.sunbeam.models.OrderStatus;
import com.sunbeam.entities.Order;
import com.sunbeam.entities.OrderItem;

public interface OrderService {

    Set<Order> createOrder(User user, Address shippingAddress, Cart cart);
    
    Order findOrderById(Long id) throws OrderException;

    List<Order> usersOrderHistory(Long userId);

    List<Order> sellersOrder(Long sellerId);

    Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception;
    
    Order cancelOrder(Long orderId, User user) throws Exception;

	OrderItem getOrderItemById(Long id) throws Exception;
	
}