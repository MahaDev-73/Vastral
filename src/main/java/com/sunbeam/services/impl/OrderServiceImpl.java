package com.sunbeam.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.daos.AddressRepository;
import com.sunbeam.daos.OrderItemRepository;
import com.sunbeam.daos.OrderRepository;
import com.sunbeam.daos.UserRepository;
import com.sunbeam.entities.Address;
import com.sunbeam.entities.Cart;
import com.sunbeam.entities.CartItem;
import com.sunbeam.entities.OrderItem;
import com.sunbeam.entities.Orders;
import com.sunbeam.entities.User;
import com.sunbeam.exceptions.OrderException;
import com.sunbeam.models.OrderStatus;
import com.sunbeam.models.PaymentStatus;
import com.sunbeam.services.CartService;
import com.sunbeam.services.OrderItemService;
import com.sunbeam.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartService cartService;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public Set<Orders> createOrder(User user, Address shippingAdress, Cart cart) {
		if (!user.getAddresses().contains(shippingAdress)) {
			user.getAddresses().add(shippingAdress);
		}
		Address address = addressRepository.save(shippingAdress);
		Map<Long, List<CartItem>> itemsBySeller = cart.getCartItems().stream()
				.collect(Collectors.groupingBy(item -> item.getProduct().getSeller().getId()));

		Set<Orders> orders = new HashSet<>();

		for (Map.Entry<Long, List<CartItem>> entry : itemsBySeller.entrySet()) {
			Long sellerId = entry.getKey();
			List<CartItem> items = entry.getValue();

			int totalOrderPrice = items.stream().mapToInt(CartItem::getSellingPrice).sum();

			int totalItem = items.stream().mapToInt(CartItem::getQuantity).sum();

			Orders createdOrder = new Orders();
			createdOrder.setUser(user);
			createdOrder.setSellerId(sellerId);
			createdOrder.setTotalMrpPrice(totalOrderPrice);
			createdOrder.setTotalSellingPrice(totalOrderPrice);
			createdOrder.setTotalItem(totalItem);
			createdOrder.setShipingAddress(address);
			createdOrder.setOrderStatus(com.sunbeam.models.OrderStatus.PENDING);
			createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

			Orders savedOrder = orderRepository.save(createdOrder);
			orders.add(savedOrder);

			List<OrderItem> orderItems = new ArrayList<>();

			for (CartItem item : items) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrder(savedOrder);
				orderItem.setMrpPrice(item.getMrpPrice());
				orderItem.setProduct(item.getProduct());
				orderItem.setQuantity(item.getQuantity());
				orderItem.setSize(item.getSize());
				orderItem.setUserId(item.getUserId());
				orderItem.setSellingPrice(item.getSellingPrice());

				savedOrder.getOrderitems().add(orderItem);
				OrderItem saveOrderItem = orderItemRepository.save(orderItem);
				orderItems.add(saveOrderItem);
			}
		}
		return orders;
	}

	@Override
	public Orders findOrderById(Long orderId) throws OrderException {
		Optional<Orders> opt = orderRepository.findById(orderId);

		if (opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("order not exist with id " + orderId);
	}

	@Override
	public List<Orders> usersOrderHistory(Long userId) {

		return orderRepository.findByUserId(userId);
	}

	@Override
	public List<Orders> getShopsOrders(Long sellerId) {

		return orderRepository.findBySellerIdOrderByOrderDateDesc(sellerId);
	}

	@Override
	public Orders updateOrderStatus(Long orderId, OrderStatus orderStatus) throws OrderException {
		Orders order = findOrderById(orderId);
		order.setOrderStatus(orderStatus);
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		Orders order = findOrderById(orderId);

		orderRepository.deleteById(orderId);

	}

	@Override
	public Orders cancelOrder(Long orderId, User user) throws OrderException {
		Orders order = this.findOrderById(orderId);
		if (user.getId() != order.getUser().getId()) {
			throw new OrderException("you can't perform this action " + orderId);
		}
		order.setOrderStatus(OrderStatus.CANCELLED);

		return orderRepository.save(order);
	}

}
