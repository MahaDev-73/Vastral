//package com.sunbeam.controllers;
//
//import com.sunbeam.entities.Orders;
//import com.sunbeam.services.OrdersService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/orders")
//public class OrdersController {
//
//    @Autowired
//    private OrdersService ordersService;
//
//    @PostMapping
//    public Orders placeOrder(@RequestBody Orders orders) {
//        return ordersService.placeOrder(orders);
//    }
//
//    @GetMapping
//    public List<Orders> getAllOrders() {
//        return ordersService.getAllOrders();
//    }
//
//    @GetMapping("/{id}")
//    public Orders getOrderById(@PathVariable Long id) {
//        return ordersService.getOrderById(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteOrder(@PathVariable Long id) {
//        ordersService.deleteOrder(id);
//    }
//}
