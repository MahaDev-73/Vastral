package com.sunbeam.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.PaymentLink;
import com.sunbeam.daos.PaymentOrderRepository;
import com.sunbeam.entities.Address;
import com.sunbeam.entities.Cart;
import com.sunbeam.entities.Order;
import com.sunbeam.entities.OrderItem;
import com.sunbeam.entities.PaymentOrder;
import com.sunbeam.entities.Seller;
import com.sunbeam.entities.SellerReport;
import com.sunbeam.entities.User;
import com.sunbeam.models.PaymentMethod;
import com.sunbeam.response.PaymentLinkResponse;
import com.sunbeam.services.CartService;
import com.sunbeam.services.OrderItemService;
import com.sunbeam.services.OrderService;
import com.sunbeam.services.PaymentService;
import com.sunbeam.services.SellerReportService;
import com.sunbeam.services.SellerService;
import com.sunbeam.services.UserService1;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	private final UserService1 userService;
	private final OrderItemService orderItemService;
	private final CartService cartService;
	private final PaymentService paymentService;
	private final PaymentOrderRepository paymentOrderRepository;
	private final SellerReportService sellerReportService;
	private final SellerService sellerService;

	
	@PostMapping()
	public ResponseEntity<?> createOrderHandler( // Use '?' for wildcard if return types differ
	        @RequestBody Address spippingAddress,
	        @RequestParam PaymentMethod paymentMethod,
	        @RequestHeader("Authorization") String jwt)
	        throws Exception {

	    User user = userService.findUserProfileByJwt(jwt);
	    Cart cart = cartService.findUserCart(user);
	    Set<Order> orders = orderService.createOrder(user, spippingAddress, cart);
	    PaymentOrder paymentOrder = paymentService.createOrder(user, orders);

	    if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
	        PaymentLinkResponse res = new PaymentLinkResponse();

	        PaymentLink payment = paymentService.createRazorpayPaymentLink(user,
	                paymentOrder.getAmount(),
	                paymentOrder.getId());
	        String paymentUrl = payment.get("short_url");
	        String paymentUrlId = payment.get("id");

	        res.setPayment_link_url(paymentUrl);
	        paymentOrder.setPaymentLinkId(paymentUrlId);
	        paymentOrderRepository.save(paymentOrder);

	        return new ResponseEntity<>(res, HttpStatus.OK);

	    } else {
	        // If it's not Razorpay, return a "Bad Request" error to the client.
	        return new ResponseEntity<>("Payment method not supported. Please use RAZORPAY.", 
	                                    HttpStatus.BAD_REQUEST);
	    }
	}
	
	@GetMapping("/user")
	public ResponseEntity< List<Order>> usersOrderHistoryHandler(
			@RequestHeader("Authorization")
	String jwt) throws Exception{
		
		User user=userService.findUserProfileByJwt(jwt);
		List<Order> orders=orderService.usersOrderHistory(user.getId());
		return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity< Order> getOrderById(@PathVariable Long orderId, @RequestHeader("Authorization")
	String jwt) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		Order orders=orderService.findOrderById(orderId);
		return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
	}

	@GetMapping("/item/{orderItemId}")
	public ResponseEntity<OrderItem> getOrderItemById(
			@PathVariable Long orderItemId, @RequestHeader("Authorization")
	String jwt) throws Exception {
		System.out.println("------- controller ");
		User user = userService.findUserProfileByJwt(jwt);
		OrderItem orderItem=orderItemService.getOrderItemById(orderItemId);
		return new ResponseEntity<>(orderItem,HttpStatus.ACCEPTED);
	}

	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<Order> cancelOrder(
			@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt
	) throws Exception {
		User user=userService.findUserProfileByJwt(jwt);
		Order order=orderService.cancelOrder(orderId,user);

		Seller seller= sellerService.getSellerById(order.getSellerId());
		SellerReport report=sellerReportService.getSellerReport(seller);

		report.setCancelOrders(report.getCancelOrders()+1);
		report.setTotalRefunds(report.getTotalRefunds()+order.getTotalSellingPrice());
		sellerReportService.updateSellerReport(report);

		return ResponseEntity.ok(order);
	}

}

