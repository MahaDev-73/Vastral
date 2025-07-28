package com.sunbeam.services.impl;

import com.sunbeam.daos.CartRepository;
import com.sunbeam.daos.OrderRepository;
import com.sunbeam.daos.PaymentOrderRepository;
import com.sunbeam.entities.Orders;
import com.sunbeam.entities.PaymentOrder;
import com.sunbeam.entities.User;
import com.sunbeam.models.PaymentOrderStatus;
import com.sunbeam.models.PaymentStatus;

import com.sunbeam.services.PaymentService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentOrderRepository paymentOrderRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Override
    public PaymentOrder createOrder(User user, Set<Orders> orders) {
        long amount = orders.stream().mapToLong(Orders::getTotalSellingPrice).sum();
        int couponPrice = cartRepository.findByUserId(user.getId()).getCouponPrice();

        PaymentOrder order = new PaymentOrder();
        order.setUser(user);
        order.setAmount(amount - couponPrice);
        order.setOrders(orders);
        order.setStatus(PaymentOrderStatus.PENDING); // Set a default status

        return paymentOrderRepository.save(order);
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {
        Optional<PaymentOrder> optionalPaymentOrder = paymentOrderRepository.findById(id);
        if (optionalPaymentOrder.isEmpty()) {
            throw new Exception("payment order not found with id " + id);
        }
        return optionalPaymentOrder.get();
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) throws Exception {
        PaymentOrder paymentOrder = paymentOrderRepository
                .findByPaymentLinkId(paymentId);

        if (paymentOrder == null) {
            throw new Exception("payment order not found with id " + paymentId);
        }
        return paymentOrder;
    }

    // @Override
    public Boolean ProceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) {
        if (paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {
            // Since there is no payment gateway integration, we can assume the payment is successful for now.
            // In a real-world scenario, you would have some other mechanism to verify payment.
            Set<Orders> orders = paymentOrder.getOrders();
            for (Orders order : orders) {
                order.setPaymentStatus(PaymentStatus.COMPLETED);
                orderRepository.save(order);
            }
            paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
            paymentOrderRepository.save(paymentOrder);
            return true;
        }
        return false;
    }

    // @Override
    // public PaymentLink createRazorpayPaymentLink(User user, Long Amount, Long orderId) throws RazorpayException {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'createRazorpayPaymentLink'");
    // }

    // @Override
    // public String createStripePaymentLink(User user, Long Amount, Long orderId) throws StripeException {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'createStripePaymentLink'");
    // }
}