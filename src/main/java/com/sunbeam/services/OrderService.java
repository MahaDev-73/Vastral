package com.sunbeam.services;

import java.util.List;
import java.util.Set;

import com.sunbeam.entities.Address;
import com.sunbeam.entities.Cart;
import com.sunbeam.entities.User;
import com.sunbeam.exceptions.OrderException;
import com.sunbeam.models.OrderStatus;
import com.sunbeam.entities.Orders;

public interface OrderService {
public Set<Orders> createOrder(User user, Address shippingAddress, Cart cart);
	
	public Orders findOrderById(Long orderId) throws OrderException;
	
	public List<Orders> usersOrderHistory(Long userId);
	
	public List<Orders>getShopsOrders(Long sellerId);

	public Orders updateOrderStatus(Long orderId,
								   OrderStatus orderStatus)
			throws OrderException;
	
	public void deleteOrder(Long orderId) throws OrderException;

	Orders cancelOrder(Long orderId,User user) throws OrderException;
	
}