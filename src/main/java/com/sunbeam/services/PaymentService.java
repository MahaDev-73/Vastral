package com.sunbeam.services;

// import com.razorpay.PaymentLink;
// import com.razorpay.RazorpayException;
// import com.stripe.exception.StripeException;
import com.sunbeam.entities.Orders;
import com.sunbeam.entities.PaymentOrder;
import com.sunbeam.entities.User;
import com.sunbeam.models.PaymentMethod;
import com.sunbeam.response.PaymentLinkResponse;

import java.util.List;
import java.util.Set;


public interface PaymentService {

    PaymentOrder createOrder(User user,
                             Set<Orders> orders);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId) throws Exception;

    // Boolean ProceedPaymentOrder (PaymentOrder paymentOrder,
    //                              String paymentId, String paymentLinkId) throws RazorpayException;

    // PaymentLink createRazorpayPaymentLink(User user,
    //                                       Long Amount,
    //                                       Long orderId) throws RazorpayException;

    // String createStripePaymentLink(User user, Long Amount,
    //                                         Long orderId) throws StripeException;
}
