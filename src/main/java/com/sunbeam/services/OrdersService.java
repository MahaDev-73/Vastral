package com.sunbeam.services;

import java.util.List;

import com.sunbeam.entities.Orders;

public interface OrdersService {
    Orders placeOrder(Orders orders);
    List<Orders> getAllOrders();
    Orders getOrderById(Long id);
    void deleteOrder(Long id);
}
